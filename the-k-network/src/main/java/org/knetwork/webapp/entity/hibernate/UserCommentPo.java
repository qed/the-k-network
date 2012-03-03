package org.knetwork.webapp.entity.hibernate;

import java.io.Serializable;
import java.util.Date;

public class UserCommentPo extends BasePo implements Serializable {

	private static final long serialVersionUID = 5114034041238284910L;
	Integer userCommentId;
	String comment;
	Date commentDate = new Date();
	String userId;
	LearningSessionPo learningSession;

	public String toString() {
		return "User comment from " + userId + " was " + comment; 
	}
	
	public Integer getUserCommentId() {
		return userCommentId;
	}

	public void setUserCommentId(Integer userCommentId) {
		this.userCommentId = userCommentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LearningSessionPo getLearningSession() {
		return learningSession;
	}

	public void setLearningSession(LearningSessionPo learningSession) {
		this.learningSession = learningSession;
	}

}
