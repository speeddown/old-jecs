package jecs.events;

public abstract class Event implements Debuggable
{
	private static final String TAG = Event.class.getSimpleName ();
	
	@Override
	public abstract void debug ();
}
