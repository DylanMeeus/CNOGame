package net.itca.game.core.spawner;

import net.itca.game.elements.MovingGameElement;

public interface SpawnBehaviour
{
	public MovingGameElement spawnElement(int maxwidth, int level);
}
