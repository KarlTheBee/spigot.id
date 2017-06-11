package de.karlthebee.spigot.id;

public abstract class AbstractEntry {
	
	/**
	 * @param innerLoop true if the entry is a inner loop entry
	 * @return the name value of this entry
	 */
	public abstract String toString(boolean innerLoop);
	
	/**
	 * @return the name value of this entry, assuming this is not an inner loop entry
	 */
	@Override
	public final String toString(){
		return this.toString(false);
	}

}
