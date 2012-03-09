package org.knetwork.webapp.entity;

import java.io.Serializable;
import java.util.Date;

public class UserBadge implements Serializable {

	private static final long serialVersionUID = 5080318996947944636L;
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
