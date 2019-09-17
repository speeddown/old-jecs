package jecs;

import jecs.util.ILoadable;
import jecs.util.ISystem;

import java.util.ArrayList;
import java.util.List;

/**
 * A GameScene is essentially a list of entities to load.
 */
public abstract class GameScene implements ILoadable
{
	protected List <Class <? extends Entity>> protoEntities = new ArrayList <> ();
	protected List <Class <? extends ISystem>> protoSystems = new ArrayList <> ();
	
	
	public List <Class <? extends Entity>> getProtoEntities ()
	{
		return protoEntities;
	}
	
	
	public List <Class <? extends ISystem>> getProtoSystems ()
	{
		return protoSystems;
	}
	
	
	public abstract void load ();
}
