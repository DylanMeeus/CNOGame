package net.itca.game.core;

import java.util.ArrayList;
import java.util.Random;

import com.codename1.ui.geom.Point;

import net.itca.game.display.EndWindow;
import net.itca.game.elements.GameElement;
import net.itca.game.elements.MovingGameElement;
import net.itca.game.elements.Player;
import net.itca.game.elements.enemies.Bomb;
import net.itca.game.factories.GameElementFactory;
import net.itca.game.interfaces.Observable;
import net.itca.game.interfaces.Observer;


/**
 * 
 * Main 'model' class, controlling the game's functions.
 * Can be thought of as the "engine" of the game.
 * @author Dylan
 */

//TODO: seperate timer for game logic.
public class Game implements Observer, Observable
{
	private ArrayList<Observer> observers;
	private ArrayList<GameElement> gameElements;
	private Player player;
	private long spawnCycles;
	private long currentCycles;
	private long bombSpawnCycles;
	private long powerupCycles;
	private int level;
	private int score;
	private int gaWidth; // gameArea width
	private int gaHeight; // gameArea height
	private int bombVelocity = 3; // Initial bomb velocity
	private IngameSpawner spawner;
	private boolean gameOver = false;
	public Game()
	{
		observers = new ArrayList<Observer>();
		spawner = new IngameSpawner();
		gameElements = new ArrayList<GameElement>();
		level = 1;
		score = 0;
		currentCycles = 0;
		bombSpawnCycles = 150;
		spawnCycles = 250;
	}
	
	
	/**
	 * Returns the players score
	 * @return int
	 */
	public int getScore()
	{
		return score;
	}
	
	/**
	 * Returns the gameOver boolean, determining if the game is "updating".
	 * @return boolean
	 */
	public boolean getGameOver()
	{
		return gameOver;
	}

	/**
	 * Adds a player to the game. 
	 * @param Player
	 */
	public void addPlayer(Player p)
	{
		player = p;
		gameElements.add(p);
	}

	/**
	 * Setting the width of the GameArea.
	 * @param param
	 */
	public void setGaWidth(int param)
	{
		gaWidth = param;
	}
	
	/**
	 * Setting the height of the GameArea
	 * @param int
	 */
	public void setGaHeight(int param)
	{
		gaHeight = param;
	}

	/**
	 * Move the player in the given direction.
	 * @param int
	 */
	public void movePlayer(int direction)
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
	 * @return Player
	 */
	public Player getPlayer()
	{
		return player;
	}

	/**
	 * Gets the elements which are in the game
	 * @return ArrayList<GameElement>
	 */
	public ArrayList<GameElement> getElements()
	{
		return gameElements;
	}

	/**
	 * Updates the game. Does one step in 'game'time, all objects change their position according to their velocity and several checks occur for collision detection and 
	 * endgame. In addition, this method also launches the "Memory Cleaner", to delete objects whom are dead.
	 */
	public void update()
	{
		currentCycles++;
		if(currentCycles%spawnCycles==0)
		{
			spawnElement();
			currentCycles = 0;
		}
		
		if(currentCycles%bombSpawnCycles==0)
		{
			spawnBomb();
		}
		for(GameElement ge : gameElements)
		{
			if(ge instanceof MovingGameElement)
			{
				MovingGameElement mge = (MovingGameElement) ge;
				mge.move();
				
				// remove elements once they fall below the screen
				if(mge.getPosition().getY() > gaHeight)
				{
					mge.setAlive(false);
				}
				
				boolean overlaps = checkCollision(mge,player);
				if(overlaps)
				{
					if(mge instanceof Bomb)
					{
						// endgame
						endGameByBomb();
					}
					mge.setAlive(false);
					score += mge.getValue();
					if(score%100==0)
					{
						level++;
						switch(level) // Increase spawnSpeed + bombVelcity (makes the game harder)
						{
							case 1: spawnCycles = 150;
							break;
							case 2: spawnCycles = 140; 
							break;
							case 3: spawnCycles = 130; bombVelocity = 4;
							break;
							case 4: spawnCycles = 120;
							break;
							case 5: spawnCycles = 110; bombVelocity = 5;
							break;
							case 6: spawnCycles = 100;
							break;
							case 7: spawnCycles = 90;
							break;
							case 8: spawnCycles = 80; bombVelocity = 6;
							break;
							case 9: spawnCycles = 70;
							break;
							case 10: spawnCycles = 60;
							break;
						}
					}
				}
			}
		}
		
		cleanupGameElements();
	}

	
	/**
	 * Method for ending the game due to beign hit by a bomb. Stops the timer for updating the game, and displays the endwindow. Also makes sure everything is garbage-collectable
	 */
	public void endGameByBomb()
	{
		gameOver = true;
		notifyObservers();
	}
	
	
	/**
	 * Checks for a collision between a MovingGameElement and a player. Returns true if their positions are overlapping.
	 * @param mge
	 * @param p
	 * @return boolean
	 */
	public boolean checkCollision(MovingGameElement mge, Player p)
	{
		//
		boolean overlaps = false;

		if (mge.getPosition().getX() < player.getPosition().getX()
				+ player.getElementImage().getWidth() /* end position */
				&& mge.getPosition().getX() > player.getPosition().getX()
				&& mge.getPosition().getY() - mge.getElementImage().getHeight()
						- 20 > player.getPosition().getY()
						- player.getElementImage().getHeight())
		{
			overlaps = true;
		}

		return overlaps;
	}

	/**
	 * Spawns a bomb in the game at a random position.
	 */
	public void spawnBomb()
	{
		Random rand = new Random();
		int width = rand.nextInt(gaWidth-50);
		Bomb bomb = GameElementFactory.createBomb(new Point(width,0), new Point(0,bombVelocity));
		bomb.setPosition(new Point(bomb.getPosition().getX(),bomb.getPosition().getY()-bomb.getElementImage().getHeight()));
		gameElements.add(bomb);
	}

	/**
	 * Spawns an element in the game at a random position.
	 */
	public void spawnElement()
	{
		// Synchronized method to avoid threading issues (gameElements is shared MVC object)
		synchronized(gameElements)
		{
			gameElements.add(spawner.getRandomElement(gaWidth-50));
		}
	}

	/**
	 * Removes elements from the game, where the isAlive variable is set to false
	 */
	public void cleanupGameElements()
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
		synchronized(observers)
		{
			for(Observer o : observers)
			{
				o.update();
			}
		}
	}

}
