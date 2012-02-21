package org.knetwork.webapp.entity;

import java.io.Serializable;
import java.util.Date;

public class Exercise implements Serializable {

	private static final long serialVersionUID = -8563576136189053083L;
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
