package jecs.events;

import jecs.Component;
import jecs.Entity;

/**
 * The type Component removed event.
 */
public class ComponentRemovedEvent extends Event
{
	private Class <? extends Component> removedComponentType;
	private Entity entity;
	
	
	/**
	 * Instantiates a new Component removed event.
	 *
	 * @param component the entity
	 * @param entity    the entity
	 */
	public ComponentRemovedEvent (Class <? extends Component> component, Entity entity)
	{
		this.removedComponentType = component;
		this.entity = entity;
	}
	
	
	/**
	 * Gets removed entity.
	 *
	 * @return the removed entity
	 */
	public Class <? extends Component> getRemovedComponentType ()
	{
		return removedComponentType;
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
		System.out.println("Component Removed Event: " +
				"\n\tComponent: " + removedComponentType.getSimpleName () + "" +
				"\n\tEntity: " + entity.getClass ().getSimpleName ());
	}
}
