package jecs.events;

/**
 * The interface Destruction event listener.
 */
public interface DestructionEventListener
{
	/**
	 * On destruction.
	 *
	 * @param event the event
	 */
	void onDestruction (DestructionEvent event);
}
