package org.knetwork.webapp.entity;

import java.io.Serializable;

public class ExerciseStates implements Serializable {

	private static final long serialVersionUID = -4108424449370968983L;
	private boolean proficient;
	private boolean reviewing;
	private boolean struggling;
	private boolean suggested;
	private boolean summative;

	public boolean isProficient() {
		return proficient;
	}

	public boolean isReviewing() {
		return reviewing;
	}

	public boolean isStruggling() {
		return struggling;
	}

	public boolean isSuggested() {
		return suggested;
	}

	public boolean isSummative() {
		return summative;
	}

}
