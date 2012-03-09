package org.knetwork.webapp.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class User implements Serializable {

	private static final long serialVersionUID = 4284784447589899327L;
	private List<String> allProficientExercises;
    private Map<String, Integer> badgeCounts;
    private List<String> coaches;
    private String nickname;
    private Integer points;
	private String userId;

	public List<String> getAllProficientExercises() {
		return allProficientExercises;
	}

	public Map<String, Integer> getBadgeCounts() {
		return badgeCounts;
	}

	public List<String> getCoaches() {
		return coaches;
	}

	public String getNickname() {
		return nickname;
	}

	public Integer getPoints() {
		return points;
	}

	public String getUserId() {
		return userId;
	}

}
