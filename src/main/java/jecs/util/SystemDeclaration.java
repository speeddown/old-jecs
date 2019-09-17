package jecs.util;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class SystemDeclaration implements Declarer<ISystem>
{
	private ArrayList<Class<? extends ISystem>> declarations = new ArrayList <> ();
	
	public SystemDeclaration(Class<? extends ISystem>...declarations)
	{
		this.declarations.addAll (Arrays.asList (declarations));
	}
}
