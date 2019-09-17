package jecs.events;

import jecs.util.ISystem;

public class SystemLoadedEvent extends Event
{
	private ISystem system = null;
	
	public SystemLoadedEvent (ISystem system)
	{
		this.system = system;
	}
	
	
	public ISystem getSystem ()
	{
		return system;
	}
	
	
	@Override
	public void debug ()
	{
		java.lang.System.out.println("System Loaded Event: " +
				"\n\tSystem: " + system.getClass ().getSimpleName ());
	}
}
