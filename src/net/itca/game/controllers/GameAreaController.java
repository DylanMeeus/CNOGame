package net.itca.game.controllers;

import java.util.ArrayList;

import net.itca.game.core.Game;
import net.itca.game.elements.GameElement;
import net.itca.game.elements.Player;

import com.codename1.ui.geom.Point;

/**
 * A class which acts as a controller between GameWindow/GameArea (View) and Game (Model)
 * @author Dylan
 *
 */
public class GameAreaController
{
	private Game game;
	public GameAreaController(Game g)
	{
		game = g;
	}
	
	/**
	 * Moves player to the left.
	 */
	public void movePlayerLeft()
	{
		game.movePlayer(0);
	}
	
	
	/**
	 * Moves player to the right.
	 */
	public void movePlayerRight()
	{
		game.movePlayer(1);
	}
	
	/**
	 * 
	 * @return Player
	 */
	public Player getPlayer()
	{
		return game.getPlayer();
	}
	
	/**
	 * 
	 * @return ArrayList<GameElement>
	 */
	public ArrayList<GameElement> getElements()
	{
		return game.getElements();
	}
	
	/**
	 * Spawns the player at the chosen position.
	 * @param startPosition
	 */
	public void createPlayer(Point startPosition)
	{
		game.addPlayer(new Player(startPosition));
	}
	
	/**
	 * 
	 * @param width
	 * @param height
	 */
	public void setScreenDimensions(int width, int height)
	{
		game.setGaHeight(height);
		game.setGaWidth(width);
	}
	
	/**
	 * Returns the player's score.
	 * @return int
	 */
	public int getScore()
	{
		return game.getScore();
	}
}
