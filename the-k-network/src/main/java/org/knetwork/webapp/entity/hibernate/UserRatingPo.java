package org.knetwork.webapp.entity.hibernate;

import java.io.Serializable;
import java.util.Date;

public class UserRatingPo extends BasePo implements Serializable {

	private static final long serialVersionUID = 5114034041238284910L;
	Integer userRatingId;
	Integer rating;
	Date ratingDate = new Date();
	String userId;
	LearningSessionPo learningSession;

	public String toString() {
		return "User rating from " + userId + " was " + rating; 
	}
	
	public Integer getUserRatingId() {
		return userRatingId;
	}

	public void setUserRatingId(Integer userRatingId) {
		this.userRatingId = userRatingId;
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

	public LearningSessionPo getLearningSession() {
		return learningSession;
	}

	public void setLearningSession(LearningSessionPo learningSession) {
		this.learningSession = learningSession;
	}

}
