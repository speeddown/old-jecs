package jecs.events;

/**
 * The interface Instantiation event listener.
 */
public interface InstantiationEventListener
{
	/**
	 * On instantiation.
	 *
	 * @param event the event
	 */
	void onInstantiation (InstantiationEvent event);
}
