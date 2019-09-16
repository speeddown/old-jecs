package jecs;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jecs.util.componentKey.ComponentKey;
import jecs.util.componentKey.KeyGroup;
import jecs.util.componentKey.KeyOperator;
import org.easymock.EasyMockSupport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class EcsTest extends EasyMockSupport
{
	private Ecs uut;
	
	
	@Before
	public void setup ()
	{
		uut = null;
	}
	
	
	@Test
	public void getInstanceReturnsNewInstance ()
	{
		uut = Ecs.getInstance ();
		
		Assert.assertNotNull ("Returned instance must not be null", uut);
	}
	
	
	
	private class TestScene extends GameScene
	{
		public TestScene ()
		{
			super (TestEntity.class);
			
			protoSystems.addAll (Arrays.asList (TestSystem.class));
		}
	}
	
	
	private class TestEntity extends Entity
	{
		public TestEntity ()
		{
			super (TestComponent.class);
		}
	}
	
	
	private class TestComponent extends Component
	{
		
		private StringProperty testProp = new SimpleStringProperty ();
		
		
		public String getTestProp ()
		{
			return testProp.get ();
		}
		
		
		public StringProperty testPropProperty ()
		{
			return testProp;
		}
		
		
		public void setTestProp (String testProp)
		{
			this.testProp.set (testProp);
		}
		
		
		@Override
		public void start ()
		{
		
		}
	}
	
	
	private class TestSystem extends SingleEntitySystem
	{
		
		public TestSystem ()
		{
			super (new ComponentKey (KeyOperator.HAS, new KeyGroup (TestComponent.class)));
		}
		
		
		@Override
		public void start ()
		{
		
		}
		
		
		@Override
		public void update ()
		{
		
		}
	}
}
