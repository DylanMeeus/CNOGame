package net.itca.game.factories;

import com.codename1.ui.geom.Point;

import net.itca.game.elements.Cloud;
import net.itca.game.elements.GameElement;
import net.itca.game.elements.enemies.Bomb;
import net.itca.game.elements.fruit.Apple;
import net.itca.game.elements.fruit.Banana;
import net.itca.game.elements.fruit.Pear;

/**
 * Statically accessible factory methods for creating game elements.
 * @author Dylan
 *
 */
public class GameElementFactory
{
	// The methods are static, so we don't need this.
	private GameElementFactory()
	{ 
		
	}
	
	/**
	 * Returns a bomb at the given position.
	 * @param position
	 * @return Bomb
	 */
	public static Bomb createBomb(Point position, Point velocity)
	{
		return new Bomb(position,velocity);
	}
	
	/**
	 * Returns an apple at the given position.
	 * @param position
	 * @return Apple
	 */
	public static Apple createApple(Point position)
	{
		return new Apple(position);
	}
	
	/**
	 * Returns a pear at the given position.
	 * @param position
	 * @return Pear
	 */
	public static Pear createPear(Point position)
	{
		return new Pear(position);
	}
	
	/**
	 * Returns a banana at the given position.
	 * @param position
	 * @return Banana
	 */
	public static Banana createBanana(Point position)
	{
		return new Banana(position);
	}
	
	/**
	 * Returns a cloud at the given position.
	 * @param position
	 * @return Cloud
	 */
	public static Cloud createCloud(Point position)
	{
		return new Cloud(position);
	}
}
