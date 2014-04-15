package net.itca.game.core;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import com.codename1.ui.geom.Point;

import net.itca.game.display.EndWindow;
import net.itca.game.elements.Apple;
import net.itca.game.elements.Banana;
import net.itca.game.elements.Bomb;
import net.itca.game.elements.GameElement;
import net.itca.game.elements.MovingGameElement;
import net.itca.game.elements.Pear;
import net.itca.game.elements.Player;
import net.itca.game.factories.GameElementFactory;
import net.itca.game.interfaces.Observer;

public class Game implements Observer
{
	ArrayList<GameElement> gameElements;
	Player player;
	long spawnCycles;
	long currentCycles;
	long bombSpawnCycles;
	long powerupCycles;
	int level;
	int score;
	int gaWidth; // gameArea width
	int gaHeight; // gameArea height
	IngameSpawner spawner;
	public Game()
	{
		spawner = new IngameSpawner();
		gameElements = new ArrayList<GameElement>();
		level = 1;
		score = 0;
		currentCycles = 0;
		bombSpawnCycles = 150;
		spawnCycles = 250;
	}

	public int getScore()
	{
		return score;
	}

	public void addPlayer(Player p)
	{
		player = p;
		gameElements.add(p);
	}

	public void setGaWidth(int param)
	{
		gaWidth = param;
	}

	public void setGaHeight(int param)
	{
		gaHeight = param;
	}

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

	public Player getPlayer()
	{
		return player;
	}

	public ArrayList<GameElement> getElements()
	{
		return gameElements;
	}

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
						EndWindow ew = new EndWindow(score);
						ew.show();
					}
					mge.setAlive(false);
					score += mge.getValue();
					if(score%100==0)
					{
						level++;
						switch(level)
						{
							case 1: spawnCycles = 150;
							break;
							case 2: spawnCycles = 140;
							break;
							case 3: spawnCycles = 130;
							break;
							case 4: spawnCycles = 120;
							break;
							case 5: spawnCycles = 110;
							break;
							case 6: spawnCycles = 100;
							break;
							case 7: spawnCycles = 90;
							break;
							case 8: spawnCycles = 80;
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

	public void spawnBomb()
	{
		Random rand = new Random();
		int width = rand.nextInt(gaWidth-50);
		Bomb bomb = GameElementFactory.createBomb((new Point(width,0)));
		bomb.setPosition(new Point(bomb.getPosition().getX(),bomb.getPosition().getY()-bomb.getElementImage().getHeight()));
		gameElements.add(bomb);
	}

	public void spawnElement()
	{
		// Synchronized method to avoid threading issues (gameElements is shared MVC object)
		synchronized(gameElements)
		{
			gameElements.add(spawner.getRandomElement(gaWidth-50));
		}
	}

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

}
