package jecs;

import jecs.builtIn.Transform;
import jecs.testClasses.TestEntity;
import jecs.testClasses.TestMultiEntitySystem;
import jecs.testClasses.TestScene;
import jecs.util.ISystem;
import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EcsTest extends EasyMockSupport
{
	private Ecs uut;
	
	GameScene mockScene;
	Entity mockEntity;
	Component mockComponent;
	ISystem mockSystem;
	
	
	@Before
	public void setup ()
	{
		uut = null;
		
		mockScene = EasyMock.createNiceMock (GameScene.class);
		mockSystem = EasyMock.createNiceMock (ISystem.class);
		mockEntity = EasyMock.createNiceMock (Entity.class);
		mockComponent = EasyMock.createNiceMock (Component.class);
	}
	
	
	@Test
	public void testGetInstanceReturnsNewInstance ()
	{
		uut = Ecs.getInstance ();
		
		Assert.assertNotNull ("Returned instance must not be null", uut);
	}
	
	
	@Test
	public void testGetInstanceWithSceneReturnsInstance ()
	{
		uut = Ecs.getInstance (TestScene.class);
		
		Assert.assertNotNull ("Returned instance must not be null!", uut);
	}
	
	
	@Test
	public void testLoadSystem ()
	{
		uut = Ecs.getInstance ();
		
		EasyMock.replay (mockSystem);
		
		EasyMock.verify (mockSystem);
	}
	
	
	@Test
	public void testInstantiate ()
	{
		uut = Ecs.getInstance ();
		
		EasyMock.expect (mockEntity.addComponent (Component.class)).andReturn (mockComponent);
		
		EasyMock.replay (mockEntity);
		
		uut.instantiate (TestEntity.class);
		
		verifyAll ();
	}
	
	@Test
	public void testStart ()
	{
		uut = Ecs.getInstance ();
		
		uut.loadScene (mockScene.getClass ());
		
		uut.start ();
		
		
		
		EasyMock.replay (mockScene);
		
		EasyMock.expect (mockScene).andVoid ();
		
		EasyMock.verify (mockScene);
	}
}
