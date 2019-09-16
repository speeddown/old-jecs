package jecs.events;

import jecs.Component;
import jecs.Entity;

/**
 * The type Component added event.
 */
public class ComponentAddedEvent extends Event
{
	private Component addedComponent;
	private Entity entity;
	
	
	/**
	 * Instantiates a new Component added event.
	 *
	 * @param component the entity
	 * @param entity    the entity
	 */
	public ComponentAddedEvent (Component component, Entity entity)
	{
		this.addedComponent = component;
		this.entity = entity;
	}
	
	
	/**
	 * Gets added entity.
	 *
	 * @return the added entity
	 */
	public Component getAddedComponent ()
	{
		return addedComponent;
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
		System.out.println("Component Added Event: " +
				"\n\tComponent: " + addedComponent.getClass ().getSimpleName () + "" +
				"\n\tEntity: " + entity.getClass ().getSimpleName ());
	}
}
