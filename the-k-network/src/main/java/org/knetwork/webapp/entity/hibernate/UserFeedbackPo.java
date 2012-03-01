package org.knetwork.webapp.entity.hibernate;

import java.io.Serializable;
import java.util.Date;
public class UserFeedbackPo extends BasePo implements Serializable {

	private static final long serialVersionUID = 5114034041238284910L;
	Integer userFeedbackId;
	Integer rating;
	Date ratingDate = new Date();
	String userId;
	String learningSessionId;

	public String toString() {
		return "User rating from " + userId + " was " + rating; 
	}
	
	public Integer getUserFeedbackId() {
		return userFeedbackId;
	}

	public void setUserFeedbackId(Integer userFeedbackId) {
		this.userFeedbackId = userFeedbackId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Date getRatingDate() {
		return ratingDate;
	}

	public void setRatingDate(Date ratingDate) {
		this.ratingDate = ratingDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLearningSessionId() {
		return learningSessionId;
	}

	public void setLearningSessionId(String learningSessionId) {
		this.learningSessionId = learningSessionId;
	}

}
