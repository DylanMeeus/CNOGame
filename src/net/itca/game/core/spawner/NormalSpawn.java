package net.itca.game.core.spawner;

import java.util.Random;

import com.codename1.ui.geom.Point;

import net.itca.game.elements.MovingGameElement;
import net.itca.game.elements.enemies.Bomb;
import net.itca.game.elements.fruit.Apple;
import net.itca.game.elements.fruit.Banana;
import net.itca.game.elements.fruit.Pear;
import net.itca.game.factories.GameElementFactory;


/**
 * Spawns fruit, bombs and powerups with varying frequency. (Bomb frequency goes up as level progresses)
 * @author Dylan
 *
 */
public class NormalSpawn implements SpawnBehaviour
{

	public MovingGameElement spawnElement(int maxwidth, int level)
	{
		Random rand = new Random();
		int width = rand.nextInt(maxwidth);
		int randElement = rand.nextInt(3+level); // the amount of elements + some extra for BOMB
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
		else // all else == bomb! (the higher the random number, the more times it will end on the 'else', thus increasing the frequency with which a bomb is dropped
		{
			// the bomb speed increases with 1 every 3 levels (3+level / 3)
			Bomb bomb = GameElementFactory.createBomb(new Point(width,0),new Point(0,3+level/3));
			bomb.setPosition(new Point(bomb.getPosition().getX(), bomb
					.getPosition().getY()
					- bomb.getElementImage().getHeight()));
			return bomb;
		}

	}

}
