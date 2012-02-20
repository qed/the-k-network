package org.knetwork.webapp.controller;

import java.net.MalformedURLException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.knetwork.webapp.BaseController;
import org.knetwork.webapp.entity.hibernate.UserFeedbackPo;
import org.knetwork.webapp.service.UserFeedbackService;
import org.knetwork.webapp.util.TypeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/user-feedback/rate")
public class UserFeedbackController extends BaseController {

	private UserFeedbackService userFeedbackService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String rate(final HttpSession session, final HttpServletRequest request, final Model model) throws MalformedURLException {
		String sRating = (String)request.getParameter("score");
		if(sRating!=null && TypeUtil.isInteger(sRating)) {
			Integer rating = Integer.parseInt(sRating);
			UserFeedbackPo uf = new UserFeedbackPo();
			uf.setRating(rating);
			logger.info(uf.toString());
			userFeedbackService.saveUserFeedback(uf);
		}
		return "user-feedback/view";
	}
	
	public UserFeedbackService getUserFeedbackService() {
		return userFeedbackService;
	}

	@Inject
	public void setUserFeedbackService(UserFeedbackService userFeedbackService) {
		this.userFeedbackService = userFeedbackService;
	}
	
}
