package org.knetwork.webapp.controller;

import java.net.MalformedURLException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.knetwork.webapp.BaseController;
import org.knetwork.webapp.entity.hibernate.UserFeedbackPo;
import org.knetwork.webapp.service.UserFeedbackService;
import org.knetwork.webapp.util.SessionMapUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserFeedbackController extends BaseController {

	private UserFeedbackService userFeedbackService;
	
	@RequestMapping(value = "user-feedback/rate")
    public String rate(final HttpSession session, final HttpServletRequest request, final Model model) throws MalformedURLException {
    	String learningSessionId = SessionMapUtil.generateLearningSessionId();
		String score = request.getParameter("score");
		
		UserFeedbackPo uf = new UserFeedbackPo();
		uf.setRating(Integer.parseInt(score));
		uf.setUserId((String)session.getAttribute("nickName"));
		uf.setLearningSessionId(learningSessionId);
		
		logger.info("score " + uf.toString());
		
		boolean alreadyExisted = userFeedbackService.saveUserFeedback(uf);
		if(alreadyExisted) {
			model.addAttribute("ratingMessage","Rating successfully changed. Thanks!");
		} else {
			model.addAttribute("ratingMessage","Rating saved, thanks!");
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
