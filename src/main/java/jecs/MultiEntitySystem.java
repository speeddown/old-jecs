package jecs;

import com.google.common.eventbus.Subscribe;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import jecs.events.ComponentAddedEvent;
import jecs.events.ComponentRemovedEvent;
import jecs.events.DestructionEvent;
import jecs.events.InstantiationEvent;
import jecs.util.ISystem;
import jecs.util.componentKey.ComponentKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public abstract class MultiEntitySystem implements ISystem
{
	/**
	 * Entities that have passed validation
	 */
	protected MapProperty <UUID, Entity> entities =
			new SimpleMapProperty <UUID, Entity> (FXCollections.observableHashMap ());
	
	protected ComponentKey componentKey = null;
	protected List <ComponentKey> componentKeys = null;
	
	
	public MultiEntitySystem (ComponentKey componentKey)
	{
		this.componentKey = componentKey;
	}
	
	
	public MultiEntitySystem (ComponentKey... keys)
	{
		this.componentKeys = new ArrayList <> ();
		this.componentKeys.addAll (Arrays.asList (keys));
	}
	
	
	@Override
	public void processEntity (Entity entity)
	{
		if (componentKeys != null)
		{
			boolean valid = true;
			
			for (ComponentKey key : componentKeys)
			{
				if (!componentKey.operate (entity))
				{
					valid = false;
					break;
				}
			}
			
			if (valid)
			{
				this.entities.put (entity.getEntityId (), entity);
			}
		}
		else
		{
			if (componentKey.operate (entity))
			{
				entities.put (entity.getEntityId (), entity);
			}
		}
	}
	
	@Subscribe
	@Override
	public void onInstantiation (InstantiationEvent event)
	{
		processEntity (event.getEntity ());
	}
	
	
	@Subscribe
	@Override
	public void onComponentRemoved (ComponentRemovedEvent event)
	{
		if (entities.containsKey (event.getEntity ().getEntityId ()))
		{
			if (componentKey.getComponentSignature ().contains (event.getRemovedComponentType ()))
			{
				processEntity (event.getEntity ());
			}
		}
	}
	
	
	@Subscribe
	@Override
	public void onComponentAdded (ComponentAddedEvent event)
	{
		if (!entities.containsKey (event.getEntity ().getEntityId ()))
		{
			if (componentKeys != null)
			{
				for (ComponentKey key : this.componentKeys)
				{
					if (componentKey.getComponentSignature ().contains (event.getAddedComponent ().getClass ()))
					{
						processEntity (event.getEntity ());
						break;
					}
				}
			}
			else
			{
				if (componentKey.getComponentSignature ().contains (event.getAddedComponent ().getClass ()))
				{
					processEntity (event.getEntity ());
				}
			}
		}
	}
	
	
	@Subscribe
	@Override
	public void onDestruction (DestructionEvent event)
	{
		entities.remove (event.getDestroyedEntity ().getEntityId ());
	}
}
