package jecs;

import jecs.testClasses.TestScene;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class MultiEntitySystemTest
{
	MultiEntitySystem uut = null;
	
	Ecs ecsMock;
	TestScene testScene;
	
	
	@Before
	public void setup ()
	{
		this.uut = null;
		this.ecsMock = null;
		this.testScene = null;
		
		this.ecsMock = EasyMock.createMock (Ecs.class);
		this.testScene = EasyMock.createMock (TestScene.class);
	}
	
	
	@Test
	public void testEmptyConstructorReturnsInstance ()
	{
	
	}
	
	
	@Test
	public void testConstructorReturnsInstance ()
	{
	}
}
