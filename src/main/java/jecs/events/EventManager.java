package jecs.events;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Event manager.
 */
public class EventManager
{
	private static EventManager instance;
	
	private EventBus eventBus;
	private List <Object> listeners = new ArrayList ();
	
	
	/**
	 * Instantiates a new Event manager.
	 */
	public EventManager ()
	{
		eventBus = new EventBus ("ECS Event Manager");
		eventBus.register (this);
	}
	
	
	/**
	 * Gets instance.
	 *
	 * @return the instance
	 */
	public static EventManager getInstance ()
	{
		if (instance == null)
		{
			instance = new EventManager ();
		}
		
		return instance;
	}
	
	
	/**
	 * Post event.
	 *
	 * @param object the object
	 */
	public void postEvent (Object object)
	{
		this.eventBus.post (object);
	}
	
	
	/**
	 * Register.
	 *
	 * @param object the object
	 */
	public void register (Object object)
	{
		this.eventBus.register (object);
		this.listeners.add (object);
	}
	
	
	/**
	 * Unregister.
	 *
	 * @param object the object
	 */
	public void unregister (Object object)
	{
		this.eventBus.unregister (object);
		this.listeners.remove (object);
	}
	
	
	/**
	 * Gets bus id.
	 *
	 * @return the bus id
	 */
	public String getBusId ()
	{
		return eventBus.toString ();
	}
	
	
	/**
	 * Gets listeners.
	 *
	 * @return the listeners
	 */
	public List <Object> getListeners ()
	{
		return this.listeners;
	}
	
	
	/**
	 * On dead event.
	 *
	 * @param deadEvent the dead event
	 */
	@Subscribe
	public void onDeadEvent (DeadEvent deadEvent)
	{
		System.out.println ("Dead Event Alarm!");
		System.out.println ("\tEvent Source: " + deadEvent.getSource ().toString ());
		System.out.println ("\tEvent Type: " + deadEvent.getEvent ().getClass ().getSimpleName ());
		System.out.println ("\tEvent: " + deadEvent.toString ());
	}
}
