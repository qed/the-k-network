package org.knetwork.webapp.entity.hibernate;

public class LearningSession {

	String learningSessionTitle;
	String learningSessionId;
	
	public LearningSession(String learningSessionTitle, String learningSessionId) {
		super();
		this.learningSessionTitle = learningSessionTitle;
		this.learningSessionId = learningSessionId;
	}
	
	public String getLearningSessionTitle() {
		return learningSessionTitle;
	}
	public void setLearningSessionTitle(String learningSessionTitle) {
		this.learningSessionTitle = learningSessionTitle;
	}
	public String getLearningSessionId() {
		return learningSessionId;
	}
	public void setLearningSessionId(String learningSessionId) {
		this.learningSessionId = learningSessionId;
	}
	
}
