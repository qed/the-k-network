package org.knetwork.webapp.controller;

import java.net.MalformedURLException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.knetwork.webapp.service.TokboxService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
// @Scope(value="session")
@RequestMapping(value="/tokbox/view")
public class TokboxController {

	private final TokboxService tokboxService;

	@Inject
	public TokboxController(TokboxService tokboxService) {
		super();
		this.tokboxService = tokboxService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String doGet(final HttpSession session, final HttpServletRequest request, final Model model) throws MalformedURLException {
		if(tokboxService==null) throw new RuntimeException("Tokbox service was null!");
		model.addAttribute("tokboxSessionId", tokboxService.getTokboxSessionId());
		model.addAttribute("apiKey", tokboxService.getApiKey());
		model.addAttribute("publisherToken", tokboxService.getPublisherToken());
		model.addAttribute("subscriberToken", tokboxService.getSubscriberToken());
		return "tokbox/view";
	}
	
}
