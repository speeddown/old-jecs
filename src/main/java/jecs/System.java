package jecs;

import com.google.common.eventbus.Subscribe;
import jecs.events.*;
import jecs.util.componentKey.ComponentKey;

import java.util.ArrayList;

/**
 * Systems act on the Components attached to Entities inside of an update method which is called from the Ecs service
 * class.
 * Which Entities a System will act on is determined by the Components attached to each Entity as well as the
 * Systems entity key. The componentKey is simply a list of entity types that the System is interested in. When
 * an Entity instantiation is announced, the Entity will be processed by all Systems.
 * <p>
 * Each System will compare its entity key to the components attached to the Entity and decide if it is valid. If so,
 * the System should operate on the Entity during each update as long as the Entity does not lose a entity that has
 * a corresponding key in the entity key.
 * <p>
 * The System class must be extended to create your own Systems. In the System super constructor, pass in the
 * entity types that the System is concerned with. Then, override the update method and work on each Entity
 * that has been found valid by your System.
 */
public abstract class System implements InstantiationEventListener, ComponentAddedListener, ComponentRemovedListener,
		DestructionEventListener
{
	protected ComponentKey componentKey = null;
	protected ArrayList <ComponentKey> componentKeys = null;
	
	
	/**
	 * Creates a System instance with no entity key
	 */
	public System ()
	{
	}
	
	
	/**
	 * Validates an Entity by comparing the entity key to the Entities attached components.
	 *
	 * @param entity the entity to validate
	 */
	public abstract void processEntity (Entity entity);
	
	/**
	 * Abstract start method that is called when the System is loaded by the Ecs service
	 */
	public abstract void start ();
	
	/**
	 * Abstract update method which is called by the Ecs service or directly
	 */
	public abstract void update ();
	
	
	@Subscribe
	public void onInstantiation (InstantiationEvent event)
	{
		processEntity (event.getEntity ());
	}
	
	@Subscribe
	public abstract void onComponentAdded (ComponentAddedEvent event);
	
	@Subscribe
	public abstract void onComponentRemoved (ComponentRemovedEvent event);
	
	@Subscribe
	public abstract void onDestruction (DestructionEvent event);
}
