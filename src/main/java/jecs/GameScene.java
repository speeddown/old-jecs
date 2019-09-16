package jecs;

import jecs.util.Declarer;

import java.util.ArrayList;
import java.util.List;

/**
 * A GameScene is essentially a list of entities to load.
 */
public abstract class GameScene
{
	private List <Class <? extends System>> protoSystems = new ArrayList <> ();
	private List <Class <? extends Entity>> protoEntities = new ArrayList <> ();
	
	public GameScene (Declarer <System> systemDeclarer, Declarer <Entity> entityDeclarer)
	{
		this.protoSystems = systemDeclarer.getDeclarations ();
		this.protoEntities = entityDeclarer.getDeclarations ();
	}
	
	
	public List <Class <? extends Entity>> getProtoEntities ()
	{
		return protoEntities;
	}
	
	public List <Class <? extends System>> getProtoSystems ()
	{
		return protoSystems;
	}
}
