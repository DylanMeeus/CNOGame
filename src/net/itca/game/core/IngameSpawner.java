package net.itca.game.core;

import java.util.Random;

import com.codename1.ui.geom.Point;

import net.itca.game.elements.GameElement;
import net.itca.game.elements.MovingGameElement;
import net.itca.game.elements.enemies.Bomb;
import net.itca.game.elements.fruit.Apple;
import net.itca.game.elements.fruit.Banana;
import net.itca.game.elements.fruit.Pear;
import net.itca.game.elements.powerups.Shield;
import net.itca.game.factories.GameElementFactory;

/**
 * Class for spawning objects ingame (To keep extra methods
 * ommited from the factory) This class also adds some "smarts" to the
 * spawning.
 * @author Dylan 
 */
public class IngameSpawner
{
	public IngameSpawner()
	{

	}

	/**
	 * Creates a random game element at a random width.
	 * 
	 * @param int
	 * @return MovingGameElement
	 */
	public MovingGameElement spawnRandomElement(int maxwidth)
	{
		Random rand = new Random();
		int width = rand.nextInt(maxwidth);
		int randElement = rand.nextInt(3);
		if (randElement == 0)
		{
			Apple apple = GameElementFactory.createApple(new Point(width,0));
			apple.setPosition(new Point(apple.getPosition().getX(), apple
					.getPosition().getY() - apple.getElementImage().getHeight()));

			return apple;
		} 
		else if (randElement == 1)
		{
			Pear pear = GameElementFactory.createPear(new Point(width, 0));
			pear.setPosition(new Point(pear.getPosition().getX(), pear
					.getPosition().getY() - pear.getElementImage().getHeight()));
			return pear;
		}
		else if (randElement == 2)
		{
			Banana banana = GameElementFactory.createBanana(new Point(width, 0));
			banana.setPosition(new Point(banana.getPosition().getX(), banana
					.getPosition().getY()
					- banana.getElementImage().getHeight()));
			return banana;
		}
		return null;
	}

	/**
	 * Creates a random power up, at a random location.
	 * @param maxwidth
	 * @return MovingGameElement (powerup)
	 */
	public MovingGameElement spawnRandomPowerup(int maxwidth)
	{
		Random rand = new Random();
		int randWidth = rand.nextInt(maxwidth);
		Shield shield = GameElementFactory.createShield(new Point(randWidth,0));
		shield.setPosition(new Point(shield.getPosition().getX(), shield
				.getPosition().getY()
				- shield.getElementImage().getHeight()));
		return shield;
	}
}
