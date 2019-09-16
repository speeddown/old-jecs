package jecs;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import jecs.events.ComponentAddedEvent;
import jecs.events.ComponentRemovedEvent;
import jecs.events.DestructionEvent;
import jecs.util.componentKey.ComponentKey;

public abstract class SingleEntitySystem<T extends Component> extends System
{
	protected ObjectProperty <Entity> entity = new SimpleObjectProperty <Entity> (null);
	
	
	public SingleEntitySystem (ComponentKey componentKey)
	{
		super ();
		
		this.componentKey = componentKey;
	}
	
	
	@Override
	public void processEntity (Entity entity)
	{
		if (componentKey.operate (entity))
		{
			this.entity.set (entity);
		}
	}
	
	
	@Override
	public void onComponentAdded (ComponentAddedEvent event)
	{
		if (entity.get () == null)
		{
			processEntity (event.getEntity ());
		}
	}
	
	
	@Override
	public void onComponentRemoved (ComponentRemovedEvent event)
	{
		if (this.entity.get () != null && event.getEntity ().getEntityId ().equals (this.entity.get ().getEntityId ()))
		{
			this.entity.set (null);
		}
	}
	
	
	@Override
	public void onDestruction (DestructionEvent event)
	{
		if (this.entity.get ().getEntityId ().equals (event.getDestroyedEntity ().getEntityId ()))
		{
			this.entity.set (null);
		}
	}
	
	
	public Entity getEntity ()
	{
		return entity.get ();
	}
}
