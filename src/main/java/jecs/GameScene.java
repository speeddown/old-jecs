package jecs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A GameScene is essentially a list of entities to load.
 */
public abstract class GameScene
{
	protected List <Class <? extends Entity>> protoEntities = new ArrayList <> ();
	protected List <Class <? extends System>> protoSystems = new ArrayList <> ();
	
	public GameScene ()
	{
	
	}
	
	public GameScene (Class<? extends Entity>...entityTypes)
	{
		this.protoEntities.addAll (Arrays.asList (entityTypes));
	}
	
	
	public List <Class <? extends Entity>> getProtoEntities ()
	{
		return protoEntities;
	}
	
	public List <Class <? extends System>> getProtoSystems () { return protoSystems; }
}
