package org.knetwork.webapp.service;

import java.util.List;

import org.knetwork.webapp.util.LearningSession;
import org.springframework.stereotype.Service;

@Service
public interface LearningSessionService {

	public List<LearningSession> getLearningSessions();

}