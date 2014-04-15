package net.itca.game.factories;

import com.codename1.ui.geom.Point;

import net.itca.game.elements.Apple;
import net.itca.game.elements.Banana;
import net.itca.game.elements.Bomb;
import net.itca.game.elements.Cloud;
import net.itca.game.elements.GameElement;
import net.itca.game.elements.Pear;

public class GameElementFactory
{
	private GameElementFactory()
	{ 
		
	}
	
	
	public static Bomb createBomb(Point position)
	{
		return new Bomb(position);
	}
	
	public static Apple createApple(Point position)
	{
		return new Apple(position);
	}
	
	public static Pear createPear(Point position)
	{
		return new Pear(position);
	}
	
	public static Banana createBanana(Point position)
	{
		return new Banana(position);
	}
	
	public static Cloud createCloud(Point position)
	{
		return new Cloud(position);
	}
}
