package org.knetwork.webapp.entity;

import java.util.List;

public class ExerciseModel {
	
	private String name;
	private List<String> covers;
	private String displayName;
	private String shortDisplayName;

	public String getName() {
		return name;
	}

	public List<String> getCovers() {
		return covers;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getShortDisplayName() {
		return shortDisplayName;
	}

}
