package jecs;

import jecs.util.EntityDeclaration;
import jecs.util.SystemDeclaration;

import java.util.ArrayList;

public class IntroScene extends GameScene
{
	public IntroScene ()
	{
		super (new SystemDeclaration ()
		{
			@Override
			public ArrayList <Class <? extends System>> getDeclarations ()
			{
				return null;
			}
		}, new EntityDeclaration ()
		{
			@Override
			public ArrayList <Class <? extends Entity>> getDeclarations ()
			{
				return null;
			}
		});
	}
}
