package org.knetwork.webapp.entity;

import java.util.Date;

public class Exercise {
	
	private String exercise;
	private ExerciseModel exerciseModel;
	private ExerciseStates exerciseStates;
	private Date lastDone;

	public String getExercise() {
		return exercise;
	}

	public ExerciseModel getExerciseModel() {
		return exerciseModel;
	}

	public ExerciseStates getExerciseStates() {
		return exerciseStates;
	}

	public Date getLastDone() {
		return lastDone;
	}

}
