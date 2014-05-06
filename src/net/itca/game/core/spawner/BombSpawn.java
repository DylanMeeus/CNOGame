package net.itca.game.core.spawner;

import java.util.Random;

import com.codename1.ui.geom.Point;

import net.itca.game.elements.MovingGameElement;
import net.itca.game.elements.enemies.Bomb;
import net.itca.game.factories.GameElementFactory;


/**
 * Only spawns bombs.
 * @author Dylan
 *
 */
public class BombSpawn implements SpawnBehaviour
{

	public MovingGameElement spawnElement(int maxwidth, int level)
	{
		Random rand = new Random();
		int width = rand.nextInt(maxwidth);
		Bomb bomb = GameElementFactory.createBomb(new Point(width,0),new Point(0,3+level/3));
		bomb.setPosition(new Point(bomb.getPosition().getX(), bomb
				.getPosition().getY()
				- bomb.getElementImage().getHeight()));
		return bomb;
	}

}
