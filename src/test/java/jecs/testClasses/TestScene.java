package jecs.testClasses;

import jecs.Ecs;
import jecs.GameScene;

public class TestScene extends GameScene
{
	@Override
	public void load ()
	{
		Ecs ecs = Ecs.getInstance ();
		
		ecs.loadSystem (TestSingleEntitySystem.class);
		ecs.loadSystem (TestMultiEntitySystem.class);
		
		ecs.instantiate (TestEntity.class);
	}
}
