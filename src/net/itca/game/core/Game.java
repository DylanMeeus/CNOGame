package net.itca.game.core;

import java.util.ArrayList;
import net.itca.game.core.spawner.BombSpawn;
import net.itca.game.core.spawner.IngameSpawner;
import net.itca.game.core.spawner.NormalSpawn;
import net.itca.game.elements.GameElement;
import net.itca.game.elements.MovingGameElement;
import net.itca.game.elements.Player;
import net.itca.game.elements.enemies.Bomb;
import net.itca.game.elements.powerups.Shield;
import net.itca.game.interfaces.Observable;
import net.itca.game.interfaces.Observer;

/**
 * 
 * Main 'model' class, controlling the game's functions. Can be thought of as
 * the "engine" of the game.
 * 
 * @author Dylan
 */

// TODO: seperate timer for game logic.
// TODO: Fix screen rotation + add support for different screen sizes
public class Game implements Observer, Observable
{
	private ArrayList<Observer> observers;
	private ArrayList<GameElement> gameElements;
	private Player player;
	private long spawnCycles;
	private long currentCycles;
	private int level;
	private int score;
	private int gaWidth; // gameArea width
	private int gaHeight; // gameArea height
	private IngameSpawner spawner;
	private boolean gameOver = false;
	private boolean shielded; // Indicates whether or not the player is protected
								// by a shield.
	private GameMode gameMode;

	public Game(GameMode gm)
	{
		gameMode = gm;
		observers = new ArrayList<Observer>();
		gameElements = new ArrayList<GameElement>();
		level = 1;
		score = 0;
		currentCycles = 0;
		spawnCycles = 250;
	}

	/**
	 * Returns the player's score
	 * 
	 * @return int
	 */
	public int getScore()
	{
		return score;
	}

	/**
	 * Returns the gameOver boolean, determining if the game is "updating".
	 * 
	 * @return boolean
	 */
	public boolean getGameOver()
	{
		return gameOver;
	}

	/**
	 * Returns the getShielded boolean. If active, the player can survive a bomb
	 * impact.
	 * 
	 * @return boolean
	 */
	public boolean getShielded()
	{
		return shielded;
	}

	/**
	 * Adds a player to the game.
	 * 
	 * @param Player
	 */
	public void addPlayer(Player p)
	{
		player = p;
		gameElements.add(p);
	}

	/**
	 * Setting the width of the GameArea. Also sets the width and behaviour for
	 * the spawner
	 * 
	 * @param int
	 */
	public void setGaWidth(int param)
	{
		gaWidth = param;
		// Create spawner with a certain width
		if (gameMode == GameMode.ARCADE)
		{
			spawner = new IngameSpawner(gaWidth, new NormalSpawn());
		} else if (gameMode == GameMode.TIMED)
		{
			spawner = new IngameSpawner(gaWidth, new BombSpawn());
		}
	}

	/**
	 * Setting the height of the GameArea
	 * 
	 * @param int
	 */
	public void setGaHeight(int param)
	{
		gaHeight = param;
	}

	/**
	 * Move the player in the given direction.
	 * 
	 * @param int 
	 */
	public void movePlayer(int direction) // doesn't use the player's setters! :)
	{
		switch (direction)
		{
		case 0:
			player.moveLeft();
			break;
		case 1:
			player.moveRight();
			break;
		}
	}

	/**
	 * Returns the Player (plant)
	 * 
	 * @return Player
	 */
	public Player getPlayer()
	{
		return player;
	}

	/**
	 * Gets the elements which are in the game
	 * 
	 * @return ArrayList<GameElement>
	 */
	public ArrayList<GameElement> getElements()
	{
		return gameElements;
	}

	/**
	 * Updates the game. Does one step in 'game'time, all objects change their
	 * position according to their velocity and several checks occur for
	 * collision detection and endgame. In addition, this method also launches
	 * the "Memory Cleaner", to delete objects whom are dead (isAlive==false).
	 */
	public void update()
	{

		// If the gamemode = BOMBS - add points per second

		if (gameMode == GameMode.TIMED)
		{
			score++;
			if(score%250==0)
			{
				level++;
				spawnCycleChange(level);
			}
		}

		currentCycles++;
		if (currentCycles % spawnCycles == 0)
		{
			spawnElement();
			currentCycles = 0;
		}

		
		for (GameElement ge : gameElements)
		{
			if (ge instanceof MovingGameElement)
			{
				MovingGameElement mge = (MovingGameElement) ge;
				mge.move();

				// remove elements once they fall below the screen
				if (mge.getPosition().getY() > gaHeight)
				{
					mge.setAlive(false);
				}

				boolean overlaps = checkCollision(mge, player);
				if (overlaps)
				{
					if (mge instanceof Bomb)
					{
						if (shielded)
						{
							shielded = false;
						} else
						{
							endGameByBomb();
						}
					} else if (mge instanceof Shield)
					{
						shielded = true; // Don't need an if check because it
											// can't yet be true. (Shields don't
											// drop if there is one active)

					}
					mge.setAlive(false);
					score += mge.getValue();
					if (score % 100 == 0)
					{
						level++; // Higher level = increased difficulty. 
								 // Steady difficulty at level 10.
						spawnCycleChange(level);
					}
				}
			}
		}

		cleanupGameElements();
	}

	/**
	 * Method for ending the game due to being hit by a bomb. Stops the timer
	 * for updating the game, and displays the endwindow. Also makes sure
	 * everything is garbage-collectible
	 */
	public void endGameByBomb()
	{
		gameOver = true;
		notifyObservers();
	}
	
	/**
	 * Changes the frequency with which spawns occur. Lower spawnCycles = Higher frequency, meaning a higher dificulty.
	 * @param level
	 */
	private void spawnCycleChange(int level)
	{
		switch (level)
		{
		case 1:
			spawnCycles = 140;
			break;
		case 2:
			spawnCycles = 130;
			break;
		case 3:
			spawnCycles = 120;
			break;
		case 4:
			spawnCycles = 110;
			break;
		case 5:
			spawnCycles = 100;
			break;
		case 6:
			spawnCycles = 90;
			break;
		case 7:
			spawnCycles = 80;
			break;
		case 8:
			spawnCycles = 80;
			break;
		case 9:
			spawnCycles = 70;
			break;
		case 10:
			spawnCycles = 70;
			break;
		default:
			spawnCycles = 50;
			break;
		}
	}

	/**
	 * Checks for a collision between a MovingGameElement and a player. Returns
	 * true if their positions are overlapping.
	 * 
	 * @param MovingGameElement
	 * @param Player
	 * @return boolean
	 */
	private boolean checkCollision(MovingGameElement mge, Player p)
	{
		//
		boolean overlaps = false;

		if (mge.getPosition().getX() < player.getPosition().getX()
				+ player.getElementImage().getWidth() /* end position */
				&& mge.getPosition().getX() + mge.getElementImage().getWidth() > player
						.getPosition().getX()
				&& mge.getPosition().getY() - mge.getElementImage().getHeight()
						- 20 > player.getPosition().getY()
						- player.getElementImage().getHeight())
		{
			overlaps = true;
		}

		return overlaps;
	}

	/**
	 * Spawns an element in the game at a random position.
	 */
	private void spawnElement()
	{
		// Synchronized method to avoid threading issues (gameElements is shared
		// MVC object)
		MovingGameElement mge = spawner.spawn(level);
		if (!(mge instanceof Shield && shielded))
		{
			synchronized (gameElements)
			{
				gameElements.add(mge);
			}
		}
	}


	/**
	 * Removes elements from the game, where the isAlive variable is set to
	 * false
	 */
	private void cleanupGameElements()
	{
		ArrayList<GameElement> newList = new ArrayList<GameElement>();
		for (GameElement ge : gameElements)
		{
			if (ge instanceof MovingGameElement)
			{
				MovingGameElement mge = (MovingGameElement) ge;
				if (mge.getAlive())
				{
					newList.add(mge);
				}
			}
		}

		gameElements = newList;
		newList = null;
	}

	public void registerObserver(Observer o)
	{
		observers.add(o);
	}

	public void removeObserver(Observer o)
	{
		observers.remove(o);
	}

	public void notifyObservers()
	{
		synchronized (observers)
		{
			for (Observer o : observers)
			{
				o.update();
			}
		}
	}
}
