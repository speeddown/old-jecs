package jecs.util;

import jecs.Entity;
import jecs.events.ComponentAddedListener;
import jecs.events.ComponentRemovedListener;
import jecs.events.DestructionEventListener;
import jecs.events.InstantiationEventListener;

/**
 *
 */
public interface ISystem extends InstantiationEventListener, ComponentAddedListener, ComponentRemovedListener, DestructionEventListener
{
	/**
	 * Validates an Entity by comparing the entity key to the Entities attached components.
	 *
	 * @param entity the entity to validate
	 */
	void processEntity (Entity entity);
	
	/**
	 * Abstract start method that is called when the System is loaded by the Ecs service
	 */
	void start ();
	
	/**
	 * Abstract update method which is called by the Ecs service or directly
	 */
	void update ();
}
