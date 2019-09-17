package jecs;

import com.google.common.eventbus.Subscribe;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import jecs.events.ComponentAddedEvent;
import jecs.events.ComponentRemovedEvent;
import jecs.events.DestructionEvent;
import jecs.events.InstantiationEvent;
import jecs.util.ISystem;
import jecs.util.componentKey.ComponentKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class SingleEntitySystem<T extends Component> implements ISystem
{
	protected ObjectProperty <Entity> entity = new SimpleObjectProperty <Entity> (null);
	
	protected ComponentKey componentKey = null;
	protected List <ComponentKey> componentKeys = null;
	
	
	public SingleEntitySystem (ComponentKey componentKey)
	{
		this.componentKey = componentKey;
	}
	
	
	public SingleEntitySystem (ComponentKey... componentKeys)
	{
		this.componentKeys = new ArrayList <> ();
		this.componentKeys.addAll (Arrays.asList (componentKeys));
	}
	
	
	@Override
	public void processEntity (Entity entity)
	{
		if (componentKey.operate (entity))
		{
			this.entity.set (entity);
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
	public void onComponentAdded (ComponentAddedEvent event)
	{
		if (entity.get () == null)
		{
			processEntity (event.getEntity ());
		}
	}
	
	
	@Subscribe
	@Override
	public void onComponentRemoved (ComponentRemovedEvent event)
	{
		if (this.entity.get () != null && event.getEntity ().getEntityId ().equals (this.entity.get ().getEntityId ()))
		{
			this.entity.set (null);
		}
	}
	
	
	@Subscribe
	@Override
	public void onDestruction (DestructionEvent event)
	{
		if (this.entity.get ().getEntityId ().equals (event.getDestroyedEntity ().getEntityId ()))
		{
			this.entity.set (null);
		}
	}
}
