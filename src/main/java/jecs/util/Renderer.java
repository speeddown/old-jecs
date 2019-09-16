package jecs.util;

/**
 * The interface Renderer.
 *
 * @param <T> the type parameter
 */
public interface Renderer<T>
{
	/**
	 * Pre render.
	 */
	void preRender ();
	
	/**
	 * Render.
	 */
	void render ();
	
	/**
	 * Post render.
	 */
	void postRender ();
}
