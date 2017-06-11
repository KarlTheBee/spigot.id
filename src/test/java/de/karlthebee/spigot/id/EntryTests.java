package de.karlthebee.spigot.id;

import org.junit.Test;

import junit.framework.Assert;

//TODO more tests
public class EntryTests {

	@Test
	public void simpleTest() {
		EntryParent p = new EntryParent();
		p.append(new Entry("meta", "281"));
		p.append(new EntryParent(new Entry("sky", 15), new Entry("thing", 42)));

		Assert.assertEquals("meta: 281, (sky: 15, thing: 42)", p.toString());
	}

	@Test
	public void simpleNamedTest() {
		EntryParent p = new EntryParent();
		p.append(new Entry("meta", "281"));

		EntryParent p2 = new EntryParent("name");
		p2.append("doctor", "who");
		p2.append("the_answer", 42);

		p.append(p2);

		Assert.assertEquals("meta: 281, name (doctor: who, the_answer: 42)", p.toString());
	}

}
