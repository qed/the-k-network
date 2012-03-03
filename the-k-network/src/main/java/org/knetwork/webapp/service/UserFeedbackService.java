package org.knetwork.webapp.service;

import org.knetwork.webapp.entity.hibernate.LearningSessionPo;
import org.knetwork.webapp.entity.hibernate.UserCommentPo;
import org.knetwork.webapp.entity.hibernate.UserRatingPo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED)
public interface UserFeedbackService extends GenericService {

	/**
	 * Returns true if a rating already existed for this session
	 * and user.
	 * @param userFeedback
	 * @return
	 */
	public boolean saveUserFeedback(UserRatingPo userFeedback);
	
	public LearningSessionPo getLearningSession(String learningSessionId);
	
	public void saveUserComment(UserCommentPo userComment);
	
	public void saveLearningSession(String learningSessionId, String learningSessionTitle);
	
}
