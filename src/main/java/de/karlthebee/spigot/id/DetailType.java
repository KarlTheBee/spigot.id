package de.karlthebee.spigot.id;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum DetailType {

	/**
	 * BIOME - only on blocks
	 */
	BIOME('b', "shows biome information. Only on blocks"),
	/**
	 * LOCATION - only on blocks
	 */
	LOCATON('l', "shows information about the location. Only on blocks"),
	/**
	 * ENCHANTEMENT - only on blocks
	 */
	ENCHANTEMENT('e', "Shows information about item enchamtements"),
	/**
	 * REDSTONE - only on blocks
	 */
	REDSTONE('r', "Shows redstone details");

	private char shortValue;
	private String description;

	DetailType(char shortValue, String description) {
		this.shortValue = shortValue;
		this.description = description;
	}

	public char getShortValue() {
		return shortValue;
	}

	public String getDescription() {
		return description;
	}

	public static Set<DetailType> fromShorts(String shorts) {
		Set<DetailType> set = new HashSet<>();

		if (shorts.equalsIgnoreCase("all"))
			return new HashSet<>(Arrays.asList(values()));

		for (char c : shorts.toCharArray()) {
			set.add(fromShort(c));
		}
		return set;

	}

	public static DetailType fromShort(char shorts) {
		for (DetailType type : values()) {
			if (type.shortValue == shorts)
				return type;
		}
		return null;
	}

}
