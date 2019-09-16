package jecs;

import jecs.events.EventManager;
import jecs.events.InstantiationEvent;
import jecs.events.SystemLoadedEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Ecs service class that is responsible for managing the ECS. There must be an instance of
 * Ecs alive to make use of components, entities, and systems.
 * <p>
 * Additionally, after getting an instance with the getInstance method, the start method
 * must be called on the instance. After that it can be used to load systems and manage entities.
 * <p>
 * There are two constructors. The default constructor is used if you call getInstance with
 * no arguments. The second constructor is used if you pass in Class types that extend the
 * System abstract class. Those class types will be used in the start method to create
 * instances of the Systems reflectively. After starting, systems can be loaded with the loadSystem
 * method.
 */
public class Ecs
{
	private static Ecs instance;
	
	private EventManager eventManager = new EventManager ();
	
	private List <System> systems = new ArrayList ();
	private List <Entity> entities = new ArrayList ();
	
	private GameScene activeScene = null;
	private Class<? extends GameScene> initialSceneType = null;
	
	Ecs ()
	{
	
	}
	
	Ecs (Class<? extends GameScene> scene)
	{
		this.initialSceneType = scene;
		
		loadScene (scene);
	}
	
	public static Ecs getInstance()
	{
		if (instance == null)
		{
			instance = new Ecs ();
		}
		return instance;
	}
	
	
	public static Ecs getInstance (Class <? extends GameScene> initialSceneType)
	{
		if (instance == null)
		{
			instance = new Ecs (initialSceneType);
		}
		
		return instance;
	}
	
	public void start ()
	{
		loadScene (this.initialSceneType);
		
		for (System system : systems)
		{
			system.start ();
		}
	}
	
	public void update ()
	{
		for (System system : systems)
		{
			system.update ();
		}
	}
	
	private void reset ()
	{
		this.systems.clear ();
		this.entities.clear ();
	}
	
	
	private void loadScene (Class<? extends GameScene> sceneType)
	{
		reset ();
		
		try
		{
			activeScene = sceneType.newInstance ();
		} catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace ();
		}
		
		if (activeScene != null)
		{
			for (Class<? extends System> protoSystem : activeScene.getProtoSystems ())
			{
				try
				{
					System newSystem = protoSystem.newInstance ();
					newSystem.start ();
					systems.add (newSystem);
					eventManager.postEvent (new SystemLoadedEvent (newSystem));
				} catch (InstantiationException | IllegalAccessException e)
				{
					e.printStackTrace ();
				}
			}
			
			for (Class<? extends Entity> protoEntity : activeScene.getProtoEntities ())
			{
				try
				{
					Entity entity = protoEntity.newInstance ();
					entities.add (entity);
					eventManager.postEvent (new InstantiationEvent (entity));
				} catch (IllegalAccessException | InstantiationException e)
				{
					e.printStackTrace ();
				}
			}
		}
		else
		{
			java.lang.System.out.println ("Active scene is null!");
		}
		
	}
	
	public <T extends System> T loadSystem (Class<T> systemType)
	{
		try
		{
			T newSystem = systemType.newInstance ();
			eventManager.postEvent (newSystem);
			if (entities.size () > 0)
			{
				for (Entity entity : entities)
				{
					newSystem.processEntity (entity);
				}
			}
			systems.add (newSystem);
			newSystem.start ();
			return newSystem;
		} catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace ();
		}
		
		return null;
	}
	
	public <T extends Entity> T instantiate (Class<T> entityType)
	{
		try
		{
			T newEntity = entityType.newInstance ();
			entities.add (newEntity);
			eventManager.postEvent (new InstantiationEvent (newEntity));
			return newEntity;
		} catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace ();
		}
		
		return null;
	}
	
	public <T extends System> T getSystem (Class<T> systemType)
	{
		for (System system : systems)
		{
			if (system.getClass ().equals (systemType))
			{
				return (T)system;
			}
		}
		
		return null;
	}
}
