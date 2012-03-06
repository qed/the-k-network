package org.knetwork.webapp.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.knetwork.webapp.entity.hibernate.LearningSessionPo;
import org.knetwork.webapp.util.LearningSession;
import org.knetwork.webapp.util.SessionMapUtil;
import org.springframework.stereotype.Service;

@Service
public class LearningSessionServiceImpl extends GenericServiceImpl implements LearningSessionService {

	/* (non-Javadoc)
	 * @see org.knetwork.webapp.service.LearningSessionService#getLearningSessions()
	 */
	public List<LearningSession> getLearningSessions() {
		Calendar nowCal = Calendar.getInstance();
		nowCal.add(Calendar.MINUTE, 45);
		
		List<LearningSession> sessions = new ArrayList<LearningSession>();
		for (String key : SessionMapUtil.sessionMap.keySet()) {
			LearningSessionPo foundLs = (LearningSessionPo)getDao().findById(LearningSessionPo.class, key);
			if(foundLs!=null && foundLs.getSessionDate() != null) {
				Calendar sessionCal = Calendar.getInstance();
				sessionCal.setTime(foundLs.getSessionDate());
				if(!sessionCal.after(nowCal)) {
					sessions.add(new LearningSession(foundLs.getLearningSessionId(), foundLs.getLearningSessionTitle()));
				}
			}
		}
		return sessions;
	}
	
}
