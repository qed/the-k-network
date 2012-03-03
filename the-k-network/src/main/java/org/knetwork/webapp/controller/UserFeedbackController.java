package org.knetwork.webapp.controller;

import java.net.MalformedURLException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.knetwork.webapp.BaseController;
import org.knetwork.webapp.entity.hibernate.UserCommentPo;
import org.knetwork.webapp.entity.hibernate.UserRatingPo;
import org.knetwork.webapp.service.UserFeedbackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserFeedbackController extends BaseController {

	private UserFeedbackService userFeedbackService;
	
	@RequestMapping(value = "user-feedback/rate")
    public String rate(final HttpSession session, final HttpServletRequest request, final Model model) throws MalformedURLException {
		String score = request.getParameter("score");
		
		UserRatingPo uf = new UserRatingPo();
		uf.setRating(Integer.parseInt(score));
		uf.setUserId((String)session.getAttribute("nickName"));
		uf.setLearningSession(userFeedbackService.getLearningSession((String)session.getAttribute("learningSessionId")));
		
		logger.info("score " + uf.toString());
		
		boolean alreadyExisted = userFeedbackService.saveUserFeedback(uf);
		if(alreadyExisted) {
			model.addAttribute("ratingMessage","Rating successfully changed. Thanks!");
		} else {
			model.addAttribute("ratingMessage","Rating saved, thanks!");
		}
		
		return "user-feedback/view";
	}
	
	@RequestMapping(value = "user-feedback/comment")
    public String comment(final HttpSession session, final HttpServletRequest request, final Model model) throws MalformedURLException {
		String comment = request.getParameter("comment");
		
		UserCommentPo uf = new UserCommentPo();
		uf.setComment(comment);
		uf.setUserId((String)session.getAttribute("nickName"));
		uf.setLearningSession(userFeedbackService.getLearningSession((String)session.getAttribute("learningSessionId")));
		
		logger.info("score " + uf.toString());
		
		userFeedbackService.saveUserComment(uf);
		
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
