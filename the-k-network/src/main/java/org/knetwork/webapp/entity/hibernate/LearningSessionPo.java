package org.knetwork.webapp.entity.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class LearningSessionPo extends BasePo {

	String learningSessionTitle;
	String learningSessionId;
	Set<UserRatingPo> userRatings = new HashSet<UserRatingPo>();
	Set<UserCommentPo> userComments = new HashSet<UserCommentPo>();
	Date sessionDate = new Date();
	
	public LearningSessionPo() {
	}
	
	public LearningSessionPo(String learningSessionId, String learningSessionTitle) {
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

	public Set<UserRatingPo> getUserRatings() {
		return userRatings;
	}

	public void setUserRatings(Set<UserRatingPo> userRatings) {
		this.userRatings = userRatings;
	}

	public Date getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(Date sessionDate) {
		this.sessionDate = sessionDate;
	}

	public Set<UserCommentPo> getUserComments() {
		return userComments;
	}

	public void setUserComments(Set<UserCommentPo> userComments) {
		this.userComments = userComments;
	}
	
}
