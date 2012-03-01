package org.knetwork.webapp.service;

import org.knetwork.webapp.entity.hibernate.UserFeedbackPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Services should be stateless in all public method calls, UNLESS...<br/>
 * They are bound to the session. Generally speaking this is avoided so to
 * keep extra things out of the sessions. However, as one may see in the
 * TokboxService object, it is preferable because there is constant 
 * external communication using the Tokbox API. Therefore, stateless calls
 * are not the way to go.
 * @author Chris
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserFeedbackServiceImpl extends GenericServiceImpl implements UserFeedbackService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public boolean saveUserFeedback(UserFeedbackPo userFeedback) {
		UserFeedbackPo examplePo = new UserFeedbackPo();
		examplePo.setUserId(userFeedback.getUserId());
		examplePo.setLearningSessionId(userFeedback.getLearningSessionId());
		int countOf = getDao().getRecordCountForExample(examplePo);
		
		if(countOf == 1) {
			examplePo.setRating(userFeedback.getRating());
			getDao().save(examplePo);
			logger.info("Found existing rating, updating.");
			return true;
		} else {
			getDao().save(userFeedback);
			logger.info("Created new rating.");
			return false;
		}
	}
	
}
