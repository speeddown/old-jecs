package jecs.events;

/**
 * The interface Component added listener.
 */
public interface ComponentAddedListener
{
	/**
	 * On entity added.
	 *
	 * @param event the event
	 */
	void onComponentAdded (ComponentAddedEvent event);
}
