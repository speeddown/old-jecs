package jecs.util;

import jecs.Component;

public interface Retrievable<T extends Class <? extends Component>>
{
	Object getValue ();
}
