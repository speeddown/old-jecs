package jecs;

/**
 * A Component is simply a data object. It has values that are related in some manner and which make them
 * interesting to specific {@link System}.
 */
public abstract class Component
{
	Entity entity;
	
	
	/**
	 * Instantiates a new Component.
	 */
	public Component ()
	{
	}
	
	
	public abstract void start ();
	
	
	public Entity getEntity ()
	{
		return this.entity;
	}
}
