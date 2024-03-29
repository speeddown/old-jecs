package jecs;

import jecs.builtIn.Transform;
import jecs.events.DestructionEvent;
import jecs.events.EventManager;
import jecs.events.InstantiationEvent;
import jecs.events.SystemLoadedEvent;
import jecs.util.ISystem;

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
	
	private EventManager eventManager = null;
	
	private List <ISystem> systems = new ArrayList ();
	private List <Entity> entities = new ArrayList ();
	
	private GameScene activeScene = null;
	private Class<? extends GameScene> initialSceneType = null;
	
	Ecs ()
	{
		eventManager = EventManager.getInstance ();
	}
	
	Ecs (Class<? extends GameScene> scene)
	{
		eventManager = EventManager.getInstance ();
		
		this.initialSceneType = scene;
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
		if (activeScene != null)
		{
			for (ISystem system : systems)
			{
				system.start ();
			}
			
			for (Entity entity : entities)
			{
				entity.start ();
			}
		}
		else
		{
			System.out.println ("No initial scene type set, cannot start Ecs!");
		}
		
	}
	
	public void update ()
	{
		for (ISystem system : systems)
		{
			system.update ();
		}
	}
	
	private void reset ()
	{
		this.systems.clear ();
		this.entities.clear ();
	}
	
	
	public void loadScene (Class<? extends GameScene> sceneType)
	{
		if (activeScene != null)
		{
			reset ();
		}
		
		try
		{
			activeScene = sceneType.newInstance ();
			if (activeScene != null)
			{
				activeScene.load ();
			}
		} catch (Exception e)
		{
			e.printStackTrace ();
		}
	}
	
	public void postEvent (Object event)
	{
		eventManager.postEvent (event);
	}
	
	public void destroy (Entity entity)
	{
		entities.remove (entity);
		eventManager.postEvent (new DestructionEvent (entity));
	}
	
	public <T extends ISystem> T loadSystem (Class<T> systemType)
	{
		try {
			T newSystem = systemType.newInstance ();
			systems.add (newSystem);
			eventManager.register (newSystem);
			postEvent (new SystemLoadedEvent (newSystem));
			return newSystem;
		} catch (Exception e) {
			e.printStackTrace ();
			return null;
		}
	}
	
	public <T extends Entity> T instantiate (Class<T> entityType)
	{
		try{
			T newEntity = entityType.newInstance ();
			entities.add (newEntity);
			
			newEntity.addComponent (Transform.class);
			
			for (Class<? extends Component> componentType : newEntity.getComponentSignature ())
			{
				if (componentType != Transform.class)
				{
					Component newComponent = componentType.newInstance ();
					newComponent.entity = newEntity;
					newEntity.addComponent (componentType);
				}
			}
			
			newEntity.start ();
			eventManager.postEvent (new InstantiationEvent (newEntity));
			return newEntity;
		} catch (Exception e)
		{
			e.printStackTrace ();
			return null;
		}
	}
	
	public <T extends ISystem> T getSystem (Class<T> systemType)
	{
		for (ISystem system : systems)
		{
			if (system.getClass ().equals (systemType))
			{
				return (T)system;
			}
		}
		
		return null;
	}
	
	public List<Entity> getEntitiesWithComponent (Class<? extends Component> componentType)
	{
		ArrayList<Entity> entities = new ArrayList <> ();
		
		for (Entity entity : this.entities)
		{
			if (entity.hasComponent (componentType))
			{
				entities.add (entity);
			}
		}
		
		return entities;
	}
	
	public List<Entity> getEntities ()
	{
		return this.entities;
	}
}
