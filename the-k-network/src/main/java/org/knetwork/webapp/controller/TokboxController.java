package org.knetwork.webapp.controller;

import java.net.MalformedURLException;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.knetwork.webapp.service.TokboxService;
import org.knetwork.webapp.util.SessionMapUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opentok.exception.OpenTokException;

@Controller
public class TokboxController {

	private final TokboxService tokboxService;

	@Inject
	public TokboxController(TokboxService tokboxService) {
		super();
		this.tokboxService = tokboxService;
	}
	
	@RequestMapping("tokbox/join")
	public String doGet(final HttpSession session, final HttpServletRequest request, final Model model) throws MalformedURLException {
		if(tokboxService==null) throw new RuntimeException("Tokbox service was null!");
		String learningSessionId = (String)session.getAttribute("learningSessionId");
		System.out.println("Tokbox pages loaded with learning session: " + learningSessionId);
		if(learningSessionId==null) {
			throw new RuntimeException("You can't run tokbox without a learning session!");
		}
		
		String tokboxSessionId = SessionMapUtil.getTokboxSessionId(learningSessionId);
		if(tokboxSessionId==null) {
			throw new RuntimeException("You can't run tokbox without a tokbox session!");
		}
		System.out.println("Tokbox pages loaded with tokbox session: " + tokboxSessionId);
		
		try {	
			Map<String, String> tokboxMap = tokboxService.getUserTokens(tokboxSessionId);
			model.addAttribute("tokboxSessionId", tokboxSessionId);
			model.addAttribute("apiKey", tokboxService.getApiKey());
			model.addAttribute("publisherToken", tokboxMap.get("publisherToken"));
			model.addAttribute("subscriberToken", tokboxMap.get("subscriberToken"));
			model.addAttribute("userToken", tokboxMap.get("userToken"));
		} catch (OpenTokException e) {
			e.printStackTrace();
		}
		return "tokbox/view";
	}
	
}
