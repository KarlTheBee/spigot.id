package de.karlthebee.spigot.id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EntryParent extends AbstractEntry {

	private String name;
	private List<AbstractEntry> entries = new ArrayList<>();

	// emptry constructor, so my IDE isn't annoying me with EntryParent(Entry
	// e1, Entry e2,...) shit
	public EntryParent() {

	}
	
	public EntryParent(String name){
		this.name=name;
	}

	public EntryParent(AbstractEntry... entries) {
		append(entries);
	}

	public void append(Object name, Object value) {
		append(new Entry(name, value));
	}

	public void append(AbstractEntry... entries) {
		for (AbstractEntry entry : entries) {
			append(entry);
		}
	}

	public void append(AbstractEntry entry) {
		this.entries.add(Objects.requireNonNull(entry));
	}

	@Override
	public String toString(boolean innerLoop) {
		StringBuilder builder = new StringBuilder();

		if (name!=null){
			builder.append(name);
			builder.append(" ");
		}
		
		if (innerLoop)
			builder.append("(");

		for (int n = 0; n < entries.size(); ++n) {
			AbstractEntry e = entries.get(n);
			builder.append(e.toString(true));
			if (n + 1 != entries.size())
				builder.append(", ");
		}

		if (innerLoop)
			builder.append(")");
		return builder.toString();
	}

}
