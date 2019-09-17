package jecs.testClasses;

import jecs.SingleEntitySystem;
import jecs.builtIn.Transform;
import jecs.events.InstantiationEvent;
import jecs.util.componentKey.ComponentKey;
import jecs.util.componentKey.KeyGroup;
import jecs.util.componentKey.KeyOperator;

public class TestSingleEntitySystem extends SingleEntitySystem
{
	public TestSingleEntitySystem ()
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
