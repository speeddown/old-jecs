package jecs.events;

import jecs.Component;

public class GlobalComponentAddedEvent
{
	private Component component;
	
	
	public GlobalComponentAddedEvent (Component component)
	{
		this.component = component;
	}
	
	
	public Component getComponent ()
	{
		return component;
	}
}
