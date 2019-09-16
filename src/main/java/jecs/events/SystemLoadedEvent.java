package jecs.events;

import jecs.System;

public class SystemLoadedEvent extends Event
{
	private System system = null;
	
	
	public SystemLoadedEvent (System system)
	{
		this.system = system;
	}
	
	
	public System getSystem ()
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
