package org.knetwork.webapp.util;

import java.util.Hashtable;
import java.util.Map;

public class SessionMapUtil {

	private static final String WHITEBOARD = "WHITEBOARD";
	private static final String TOKBOX = "TOKBOX";
	
	protected static Map<String, Map<String, String>> sessionMap = new Hashtable<String, Map<String, String>>();
	
	public static void addLearningSessionToMap(String learningSessionId) {
		if(!isLearningSessionMapped(learningSessionId)) {
			Map<String, String> apiSessionMap = new Hashtable<String, String>();
			apiSessionMap.put(WHITEBOARD, "");
			apiSessionMap.put(TOKBOX, "");
			sessionMap.put(learningSessionId, apiSessionMap);
		}
	}
	
	private static boolean isLearningSessionMapped(String learningSessionId) {
		return sessionMap.get(learningSessionId)!=null;
	}
	
	public static boolean tokboxSessionExists(String learningSessionId) {
		if(!isLearningSessionMapped(learningSessionId)) return false;
		
		return getTokboxSessionId(learningSessionId) != null;
	}
	
	public static String getTokboxSessionId(String learningSessionId) {
		if(!isLearningSessionMapped(learningSessionId)) {
			addLearningSessionToMap(learningSessionId);
			return null;
		} else {
			if(tokboxSessionExists(learningSessionId)) {
				return sessionMap.get(learningSessionId).get(TOKBOX);
			} else {
				return null;
			}
		}
	}
	
	public static void addTokboxSessionId(String learningSessionId, String tokboxSessionId) {
		if(!tokboxSessionExists(learningSessionId)) {
			sessionMap.get(learningSessionId).put(TOKBOX, tokboxSessionId);
		}
		
	}
}
