package org.knetwork.webapp.entity.hibernate;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
public class UserFeedbackPo extends BasePo implements Serializable {

	private static final long serialVersionUID = 5114034041238284910L;
	Integer userFeedbackId;
	Integer rating;
	Date ratingDate = new Date();
	String userId;

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
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
