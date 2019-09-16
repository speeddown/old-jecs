package jecs.events;

/**
 * The interface Component removed listener.
 */
public interface ComponentRemovedListener
{
	/**
	 * On entity removed.
	 *
	 * @param event the event
	 */
	void onComponentRemoved (ComponentRemovedEvent event);
}
