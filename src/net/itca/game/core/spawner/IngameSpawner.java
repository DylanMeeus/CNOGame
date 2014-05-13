package net.itca.game.core.spawner;

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
import net.itca.game.interfaces.Observer;

/**
 * Class for spawning objects into the game (To keep extra methods
 * omit from the factory) This class also adds some "smarts" to the
 * spawning system by use of SpawnBehaviours.
 * @author Dylan 
 */




public class IngameSpawner implements Observer // Observe the gameTimer to take care of the spawning entirely.
{
	SpawnBehaviour spawnBehaviour;
	int width;
	public IngameSpawner(int spawnWidth, SpawnBehaviour sb)
	{
		width = spawnWidth;
		spawnBehaviour = sb;
	}


	public MovingGameElement spawn(int level)
	{
		 return spawnBehaviour.spawnElement(width,level);
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


	public void update()
	{
		// TODO Auto-generated method stub
		
	}
}
