package jecs;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jecs.util.Declarer;
import jecs.util.EntityDeclaration;
import jecs.util.SystemDeclaration;
import jecs.util.componentKey.ComponentKey;
import jecs.util.componentKey.KeyGroup;
import jecs.util.componentKey.KeyOperator;
import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.MockType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.easymock.EasyMock.expect;

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
	
	@Test
	public void getInstanceLoadsSceneSystems ()
	{
		uut = Ecs.getInstance (TestScene.class);
		
		Assert.assertNotNull ("Ecs instance should contain TestSystem", uut.getSystem (TestSystem.class));
	}
	
	private class TestScene extends GameScene
	{
		public TestScene ()
		{
			super (() -> {
				ArrayList<Class<? extends System>> systemTypes = new ArrayList <> ();
				systemTypes.add (TestSystem.class);
				return systemTypes;
			}, () -> {
				ArrayList<Class<? extends Entity>> entityTypes = new ArrayList <> ();
				entityTypes.add (TestEntity.class);
				return entityTypes;
			});
		}
	}
	
	private class TestEntity extends Entity
	{
		public TestEntity ()
		{
			super(TestComponent.class);
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
