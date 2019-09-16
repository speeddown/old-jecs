package jecs.util.componentKey;

import jecs.Component;
import jecs.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComponentKey implements GroupOperable
{
	private KeyOperator operator = null;
	private KeyGroup keyGroup = null;
	private ArrayList <KeyGroup> keyGroups = null;
	private ArrayList <Class <? extends Component>> keys = null;
	private Class <? extends Component> key = null;
	
	
	public ComponentKey (KeyOperator operator, KeyGroup... keyGroups)
	{
		this.keyGroups = new ArrayList ();
		this.keyGroups.addAll (Arrays.asList (keyGroups));
		this.operator = operator;
	}
	
	
	public ComponentKey (KeyOperator operator, KeyGroup keyGroup)
	{
		this.operator = operator;
		this.keyGroup = keyGroup;
	}
	
	
	@Override
	public boolean operate (Entity entity)
	{
		switch (operator)
		{
			case AND:
				if (!(keys == null))
				{
					for (Class <? extends Component> key : keys)
					{
						if (!entity.hasComponent (key))
						{
							return false;
						}
					}
				}
				else
				{
					return entity.hasComponent (key);
				}
			
			case OR:
				if (!(keys == null))
				{
					for (Class <? extends Component> key : keys)
					{
						if (entity.hasComponent (key))
						{
							return true;
						}
					}
				}
				
				return false;
			
			case NOT:
				if (!(keys == null))
				{
					for (Class <? extends Component> key : keys)
					{
						if (entity.hasComponent (key))
						{
							return false;
						}
					}
				}
				else
				{
					return entity.hasComponent (key);
				}
			
			case ANY:
				return true;
			
			default:
				return false;
		}
	}
	
	public List <Class<? extends Component>> getComponentSignature ()
	{
		return this.keys;
	}
}
