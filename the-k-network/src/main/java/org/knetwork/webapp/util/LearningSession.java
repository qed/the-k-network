package org.knetwork.webapp.util;

public class LearningSession {

	private String sessionId;
	private String sessionTitle;

	public LearningSession(String sessionId, String sessionTitle) {
		super();
		this.sessionId = sessionId;
		this.sessionTitle = sessionTitle;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionTitle() {
		return sessionTitle;
	}

	public void setSessionTitle(String sessionTitle) {
		this.sessionTitle = sessionTitle;
	}

}
