package jecs.util;

import java.util.ArrayList;

public interface Declarer<T>
{
	ArrayList<Class<? extends T>> getDeclarations();
}
