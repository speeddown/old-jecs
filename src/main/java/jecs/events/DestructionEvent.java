package jecs.events;

import jecs.Entity;

/**
 * The type Destruction event.
 */
public class DestructionEvent extends Event
{
	private Entity destroyedEntity;
	
	
	/**
	 * Instantiates a new Destruction event.
	 *
	 * @param destroyedEntity the destroyed entity
	 */
	public DestructionEvent (Entity destroyedEntity)
	{
		this.destroyedEntity = destroyedEntity;
	}
	
	
	/**
	 * Gets destroyed entity.
	 *
	 * @return the destroyed entity
	 */
	public Entity getDestroyedEntity ()
	{
		return destroyedEntity;
	}
	
	
	@Override
	public void debug ()
	{
		System.out.println ("Destruction Event: " +
				"\n\tDestroyed Entity: " + destroyedEntity.getClass ().getSimpleName ());
	}
}
