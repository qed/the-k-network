package org.knetwork.webapp.entity;

import java.io.Serializable;

public class TargetContext implements Serializable {

	private static final long serialVersionUID = 4064897682862251969L;
	private String name;
	private String displayName;
	private String shortDisplayName;

	public String getName() {
		return name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getShortDisplayName() {
		return shortDisplayName;
	}

}
