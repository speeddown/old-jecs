package jecs.builtIn;

import com.badlogic.gdx.math.Vector3;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import jecs.Component;

public class Transform extends Component
{
	private ObjectProperty <Vector3> worldPosition = new SimpleObjectProperty <Vector3> (new Vector3 (0, 0, 0));
	private ObjectProperty <Vector3> size = new SimpleObjectProperty <Vector3> (new Vector3 (1, 1, 0));
	private ObjectProperty <Vector3> translation = new SimpleObjectProperty <Vector3> (null);
	private BooleanProperty boundToWorld = new SimpleBooleanProperty (false);
	private ObjectProperty <Vector3> origin = new SimpleObjectProperty <Vector3> (new Vector3 (0, 0, 0));
	
	
	@Override
	public void start ()
	{
	}
	
	
	public Vector3 getWorldPosition ()
	{
		return worldPosition.get ();
	}
	
	
	public ObjectProperty <Vector3> worldPositionProperty ()
	{
		return worldPosition;
	}
	
	
	public void setWorldPosition (Vector3 worldPosition)
	{
		this.worldPosition.set (worldPosition);
	}
	
	
	public Vector3 getSize ()
	{
		return size.get ();
	}
	
	
	public ObjectProperty <Vector3> sizeProperty ()
	{
		return size;
	}
	
	
	public void setSize (Vector3 size)
	{
		this.size.set (size);
	}
	
	
	public Vector3 getTranslation ()
	{
		return translation.get ();
	}
	
	
	public ObjectProperty <Vector3> translationProperty ()
	{
		return translation;
	}
	
	
	public void setTranslation (Vector3 translation)
	{
		this.translation.set (translation);
	}
	
	
	public boolean isBoundToWorld ()
	{
		return boundToWorld.get ();
	}
	
	
	public BooleanProperty boundToWorldProperty ()
	{
		return boundToWorld;
	}
	
	
	public void setBoundToWorld (boolean boundToWorld)
	{
		this.boundToWorld.set (boundToWorld);
	}
	
	
	@Override
	public boolean equals (Object other)
	{
		if (other instanceof Transform)
		{
			Transform otherTransform = (Transform)other;
			return otherTransform.getWorldPosition ().x == worldPosition.get ().x && otherTransform.getWorldPosition ().y == worldPosition
					.get ().y;
		}
		
		return false;
	}
	
	
	public Vector3 getOrigin ()
	{
		return origin.get ();
	}
	
	
	public ObjectProperty <Vector3> originProperty ()
	{
		return origin;
	}
	
	
	public void setOrigin (Vector3 origin)
	{
		this.origin.set (origin);
	}
}
