package jecs.util.componentKey;

import jecs.Component;
import jecs.Entity;

import java.util.ArrayList;
import java.util.Arrays;

public class KeyGroup implements Operable
{
	private ArrayList <Class <? extends Component>> keys = new ArrayList <Class <? extends Component>> ();
	
	
	public KeyGroup (Class <? extends Component>... keys)
	{
		this.keys.addAll (Arrays.asList (keys));
	}
	
	
	public ArrayList <Class <? extends Component>> getKeys ()
	{
		return this.keys;
	}
	
	
	@Override
	public boolean operate (KeyOperator operator, Entity entity)
	{
		switch (operator)
		{
			case AND:
				for (Class <? extends Component> key : keys)
				{
					if (!entity.hasComponent (key))
					{
						return false;
					}
				}
				
				return true;
			
			case OR:
				for (Class <? extends Component> key : keys)
				{
					if (entity.hasComponent (key))
					{
						return true;
					}
				}
				
				return false;
			
			case HAS:
				return entity.hasComponent (keys.get (0));
			
			case NOT:
				boolean doesNot = true;
				
				for (Class <? extends Component> key : keys)
				{
					if (entity.hasComponent (key))
					{
						return false;
					}
				}
				
				return true;
			
			default:
				return false;
		}
	}
}
