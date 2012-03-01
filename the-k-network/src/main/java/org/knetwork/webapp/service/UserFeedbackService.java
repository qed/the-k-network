package org.knetwork.webapp.service;

import org.knetwork.webapp.entity.hibernate.UserFeedbackPo;
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
	public boolean saveUserFeedback(UserFeedbackPo userFeedback);
	
}
