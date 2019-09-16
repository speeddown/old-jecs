package jecs.util;

import jecs.System;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class SystemDeclaration implements Declarer<System>
{
	private ArrayList<Class<? extends System>> declarations = new ArrayList <> ();
	
	public SystemDeclaration(Class<? extends System>...declarations)
	{
		this.declarations.addAll (Arrays.asList (declarations));
	}
}
