package de.karlthebee.spigot.id;

import java.util.Objects;

public class Entry extends AbstractEntry{

	private final String name;
	private final String value;
	

	public Entry(Object name, Object value) {
		super();
		this.name = Objects.requireNonNull(name).toString();
		this.value = Objects.requireNonNull(value).toString();
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString(boolean innerLoop) {
		return name + ": " + value;
	}

}
