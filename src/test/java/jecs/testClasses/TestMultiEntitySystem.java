package jecs.testClasses;

import jecs.MultiEntitySystem;
import jecs.builtIn.Transform;
import jecs.events.InstantiationEvent;
import jecs.util.componentKey.ComponentKey;
import jecs.util.componentKey.KeyGroup;
import jecs.util.componentKey.KeyOperator;

public class TestMultiEntitySystem extends MultiEntitySystem
{
	public TestMultiEntitySystem ()
	{
		super (new ComponentKey (KeyOperator.HAS, new KeyGroup (Transform.class)));
	}
	
	@Override
	public void start ()
	{
	
	}
	
	
	@Override
	public void update ()
	{
	
	}
	
	
	@Override
	public void onInstantiation (InstantiationEvent event)
	{
	
	}
}
