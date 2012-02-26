package org.knetwork.webapp.util;

import java.util.Hashtable;
import java.util.Map;

public class SessionMapUtil {

	private static final String WHITEBOARD = "WHITEBOARD";
	private static final String TOKBOX = "TOKBOX";
	
	protected static Map<String, Map<String, String>> sessionMap = new Hashtable<String, Map<String, String>>();
	
	public static void addLearningSessionToMap(String learningSessionId) {
		if(sessionMap.get(learningSessionId)==null) {
			Map<String, String> apiSessionMap = new Hashtable<String, String>();
			apiSessionMap.put(WHITEBOARD, "");
			apiSessionMap.put(TOKBOX, "");
			sessionMap.put(learningSessionId, apiSessionMap);
		}
	}
	
	public static String getTokboxSessionId(String learningSessionId) {
		return null;
	}
}
