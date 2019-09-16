package jecs.util.componentKey;

import jecs.Entity;

public interface Operable
{
	boolean operate (KeyOperator operator, Entity entity);
}
