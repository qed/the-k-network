package org.knetwork.webapp.entity;

import java.util.Date;

public class UserBadge {

	private String badgeName;
	private Date date;
	private Integer pointsEarned;
	private String user;
	private TargetContext targetContext;

	public String getBadgeName() {
		return badgeName;
	}

	public Date getDate() {
		return date;
	}

	public Integer getPointsEarned() {
		return pointsEarned;
	}

	public String getUser() {
		return user;
	}

	public TargetContext getTargetContext() {
		return targetContext;
	}

}
