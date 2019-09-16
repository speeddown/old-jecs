package jecs.events;

import jecs.Entity;

/**
 * The type Instantiation event.
 */
public class InstantiationEvent extends Event
{
	private Entity entity;
	
	
	/**
	 * Instantiates a new Instantiation event.
	 *
	 * @param entity the entity
	 */
	public InstantiationEvent (Entity entity)
	{
		this.entity = entity;
	}
	
	
	/**
	 * Gets entity.
	 *
	 * @return the entity
	 */
	public Entity getEntity ()
	{
		return entity;
	}
	
	
	@Override
	public void debug ()
	{
		System.out.println ("Instantiation Event: " +
				"\n\tEntity: " + entity.getClass ().getSimpleName ());
	}
}
