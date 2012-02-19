package org.knetwork.webapp.service;

import org.knetwork.webapp.entity.hibernate.UserFeedbackPo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserFeedbackServiceImpl extends GenericServiceImpl implements UserFeedbackService {

	public void saveUserFeedback(UserFeedbackPo userFeedback) {
		getDao().save(userFeedback);
	}
	
}
