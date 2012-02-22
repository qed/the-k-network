package org.knetwork.webapp.controller;

import javax.inject.Inject;

import org.knetwork.webapp.BaseController;
import org.knetwork.webapp.entity.hibernate.UserFeedbackPo;
import org.knetwork.webapp.service.UserFeedbackService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/user-feedback/rate")
public class UserFeedbackController extends BaseController {

	private UserFeedbackService userFeedbackService;

	@RequestMapping(method = RequestMethod.GET)
	public String rate(Integer score) {
		UserFeedbackPo uf = new UserFeedbackPo();
		uf.setRating(score);
		logger.info(uf.toString());
		userFeedbackService.saveUserFeedback(uf);
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
