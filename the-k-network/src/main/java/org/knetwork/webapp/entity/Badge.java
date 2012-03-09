package org.knetwork.webapp.entity;

import java.io.Serializable;
import java.util.List;

public class Badge implements Serializable {

	private static final long serialVersionUID = -3808444217093145053L;
	private Integer badgeCategory;
	private String name;
	private String description;
	private Integer points;
	private String safeExtendedDescription;
	private List<UserBadge> userBadges;

	public Integer getBadgeCategory() {
		return badgeCategory;
	}

	public void setBadgeCategory(Integer badgeCategory) {
		this.badgeCategory = badgeCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getSafeExtendedDescription() {
		return safeExtendedDescription;
	}

	public void setSafeExtendedDescription(String safeExtendedDescription) {
		this.safeExtendedDescription = safeExtendedDescription;
	}

	public List<UserBadge> getUserBadges() {
		return userBadges;
	}

	public void setUserBadges(List<UserBadge> userBadges) {
		this.userBadges = userBadges;
	}

}
