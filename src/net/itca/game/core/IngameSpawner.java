package net.itca.game.core;

import java.util.Random;

import com.codename1.ui.geom.Point;

import net.itca.game.elements.GameElement;
import net.itca.game.elements.fruit.Apple;
import net.itca.game.elements.fruit.Banana;
import net.itca.game.elements.fruit.Pear;


/**
 * 
 * @author Dylan
 * Class for spawning objects ingame (To keep extra methods ommited from the factory)
 * This class also adds some "smarts" to the spawning.
 */
public class IngameSpawner
{
	public IngameSpawner()
	{
		
	}
	
	/**
	 * Creates a gameelement at a random width
	 * @param width
	 * @return GameElement
	 */
	public GameElement getRandomElement(int param)
	{
		Random rand = new Random();
		int width = rand.nextInt(param);
		int randElement = rand.nextInt(3);
		if (randElement == 0)
		{
			Apple apple = new Apple(new Point(width, 0));
			apple.setPosition(new Point(apple.getPosition().getX(), apple
					.getPosition().getY() - apple.getElementImage().getHeight()));
			
			return apple;
		} else if (randElement == 1)
		{
			Pear pear = new Pear(new Point(width, 0));
			pear.setPosition(new Point(pear.getPosition().getX(), pear
					.getPosition().getY() - pear.getElementImage().getHeight()));
			return pear;
		} else if (randElement == 2)
		{
			Banana banana = new Banana(new Point(width, 0));
			banana.setPosition(new Point(banana.getPosition().getX(), banana
					.getPosition().getY()
					- banana.getElementImage().getHeight()));
			return banana;
		}
		
		return null;
	}
}
