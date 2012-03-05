package org.knetwork.webapp.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SessionMapUtil {

	private static final String WHITEBOARD = "WHITEBOARD";
	private static final String TOKBOX = "TOKBOX";
	private static final String SESSION_TITLE = "SESSION_TITLE";

	protected static Map<String, Map<String, String>> sessionMap = new Hashtable<String, Map<String, String>>();

	public static synchronized String generateLearningSessionId() {
		String uuid = UUID.randomUUID().toString();
		addLearningSessionToMap(uuid);
		return uuid;
	}

	public static void addLearningSessionToMap(String learningSessionId) {
		if (!isLearningSessionMapped(learningSessionId)) {
			Map<String, String> apiSessionMap = new Hashtable<String, String>();
			apiSessionMap.put(WHITEBOARD, "");
			apiSessionMap.put(TOKBOX, "");
			sessionMap.put(learningSessionId, apiSessionMap);
		}
	}
	
	public static void setSessionTitle(String learningSessionId, String sessionTitle) {
		if (isLearningSessionMapped(learningSessionId)) {
			Map<String, String> apiSessionMap = sessionMap.get(learningSessionId);
			apiSessionMap.put(SESSION_TITLE, sessionTitle);
			sessionMap.put(learningSessionId, apiSessionMap);
		}
	}
	
	public static String getSessionTitle(String learningSessionId) {
		if (isLearningSessionMapped(learningSessionId)) {
			Map<String, String> apiSessionMap = sessionMap.get(learningSessionId);
			return apiSessionMap.get(SESSION_TITLE);
		} else {
			return "";
		}
	}
	
	private static boolean isLearningSessionMapped(String learningSessionId) {
		return sessionMap.get(learningSessionId) != null;
	}

	public static boolean tokboxSessionExists(String learningSessionId) {
		return getTokboxSessionId(learningSessionId) != null;
	}

	public static String getTokboxSessionId(String learningSessionId) {
		if (!isLearningSessionMapped(learningSessionId)) {
			addLearningSessionToMap(learningSessionId);
			return null;
		} else {
			return sessionMap.get(learningSessionId).get(TOKBOX);
		}
	}

	public static void addTokboxSessionId(String learningSessionId,
			String tokboxSessionId) {
		sessionMap.get(learningSessionId).put(TOKBOX, tokboxSessionId);
	}

	public static List<LearningSession> getLearningSessions() {
		List<LearningSession> sessions = new ArrayList<LearningSession>();
		for (String key : sessionMap.keySet()) {
			LearningSession ls = new LearningSession(key, getSessionTitle(key));
			sessions.add(ls);
		}
		return sessions;
	}
}
