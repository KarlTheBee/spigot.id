package de.karlthebee.spigot.id;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import junit.framework.Assert;

public class DetailTypeTest {

	@Test
	public void allTest() {
		Set<DetailType> allTypes = new HashSet<>(Arrays.asList(DetailType.values()));
		Assert.assertEquals(allTypes, DetailType.fromShorts("brle"));
		Assert.assertEquals(allTypes, DetailType.fromShorts("all"));
	}
	
	@Test
	public void duplicateArgsTest(){
		Assert.assertEquals(DetailType.fromShorts("bbbbbbbbbbb"), DetailType.fromShorts("b"));
		Assert.assertEquals(DetailType.fromShorts("bb"), DetailType.fromShorts("b"));
	}
	
	@Test
	public void shortVsShortsTest(){
		Assert.assertEquals(DetailType.fromShort('b'), DetailType.fromShorts("b").iterator().next());
	}
	
	

}
