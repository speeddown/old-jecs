package jecs;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import jecs.events.ComponentAddedEvent;
import jecs.events.ComponentRemovedEvent;
import jecs.events.EventManager;
import jecs.events.InstantiationEvent;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

/**
 * An Entity is essentially a bag of components and does not process anything other than managing which
 * components are currently attached to it. The components attached to an Entity determine the Entity type and which
 * this is used by {@link System}s during Entity validation
 * <p>
 * Removing or adding components during runtime will cause the Entity validation to be rerun for every System that
 * is concerned so it is probably best to do this sparingly though it has not been tested for performance as of yet.
 * <p>
 * The safest and most practical means of adding components is to extend the Entity class and calling the super
 * Entity constructor which takes a variable number of class types that must extend the {@link Component} class.
 * Any class type passed in this way will cause an instance of each passed in type to be reflectively created
 * when the Entity child class is created. This is also better performance wise due to the Entity holding off on
 * announcing its instantiation as an instantiation announcement is what kicks off Entity validation for systems
 */
public class Entity
{
	private ObjectProperty <UUID> entityId = new SimpleObjectProperty (UUID.randomUUID ());
	private HashMap <Class <? extends Component>, Component> componentMap = new HashMap ();
	private EventManager eventManager = EventManager.getInstance ();
	
	
	/**
	 * Creates a new Entity instance and announces instantiation
	 */
	protected Entity ()
	{
		//		componentMap.put (Transform.class, new Transform ());
		eventManager.postEvent (new InstantiationEvent (this));
	}
	
	
	/**
	 * Creates new Entity instance and adds instances of all passed in Component class types
	 * to the Entity
	 *
	 * @param components the components types to create and add to Entity
	 */
	public Entity (Class <? extends Component>... components)
	{
		//		Transform transform = new Transform ();
		//		componentMap.put (Transform.class, transform);
		
		for (Class <? extends Component> component : components)
		{
			try
			{
				Component newComponent = component.newInstance ();
				newComponent.entity = this;
				componentMap.put (component, newComponent);
				newComponent.start ();
			} catch (Exception e)
			{
				e.printStackTrace ();
			}
		}
	}
	
	
	/**
	 * Returns the Entitys UUID
	 *
	 * @return the entity id
	 */
	public UUID getEntityId ()
	{
		return entityId.get ();
	}
	
	
	/**
	 * Returns the Entity id property
	 *
	 * @return the id object property
	 */
	public ObjectProperty <UUID> entityIdProperty ()
	{
		return entityId;
	}
	
	
	/**
	 * Adds an instance of the passed in Component type to the Entity
	 *
	 * @param <T>           the Component type to add
	 * @param componentType the entity type
	 * @return the created Component instance
	 */
	public <T extends Component> T addComponent (Class <T> componentType)
	{
		T component = null;
		try
		{
			component = componentType.getDeclaredConstructor ().newInstance ();
			componentMap.put (componentType, component);
			eventManager.postEvent (new ComponentAddedEvent (component, this));
		} catch (Exception e)
		{
			e.printStackTrace ();
		}
		
		return component;
	}
	
	
	/**
	 * Remove entity from Entity
	 *
	 * @param componentType the entity type to remove
	 */
	public void removeComponent (Class <? extends Component> componentType)
	{
		componentMap.remove (componentType);
		eventManager.postEvent (new ComponentRemovedEvent (componentType, this));
	}
	
	
	/**
	 * Returns all Components attached to Entity
	 *
	 * @return the components
	 */
	public Collection <Component> getComponents ()
	{
		return componentMap.values ();
	}
	
	
	/**
	 * Returns an attached Component based on the passed in Component type. Will return
	 * null if the Entity does not have a Component of passed in type currently attached
	 *
	 * @param <T>           the type parameter
	 * @param componentType the entity type
	 * @return the entity
	 */
	public <T extends Component> T getComponent (Class <T> componentType)
	{
		return (T)componentMap.get (componentType);
	}
	
	
	public boolean hasComponent (Class <? extends Component> key)
	{
		return componentMap.containsKey (key);
	}
}
