package org.knetwork.webapp.controller;

import javax.inject.Inject;

import org.knetwork.webapp.BaseController;
import org.knetwork.webapp.entity.hibernate.UserFeedbackPo;
import org.knetwork.webapp.service.UserFeedbackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping(value="/user-feedback")
public class UserFeedbackController extends BaseController {

	private UserFeedbackService userFeedbackService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getFeedbackForm(Model model) {
		model.addAttribute("userFeedback", new UserFeedbackPo());
		return "user-feedback/view";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
		@ModelAttribute("userFeedback") UserFeedbackPo userFeedback,
		BindingResult result, SessionStatus status) {
 
		logger.info(userFeedback.toString());
		userFeedbackService.saveUserFeedback(userFeedback);
		
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
