package net.itca.game.controllers;

import java.util.ArrayList;

import net.itca.game.core.Game;
import net.itca.game.elements.GameElement;
import net.itca.game.elements.Player;

import com.codename1.ui.geom.Point;

public class GameAreaController
{
	Game game;
	public GameAreaController(Game g)
	{
		game = g;
	}
	
	public void movePlayerLeft()
	{
		game.movePlayer(0);
	}
	
	public void movePlayerRight()
	{
		game.movePlayer(1);
	}
	
	public Player getPlayer()
	{
		return game.getPlayer();
	}
	
	public ArrayList<GameElement> getElements()
	{
		return game.getElements();
	}
	
	public void createPlayer(Point startPosition)
	{
		game.addPlayer(new Player(startPosition));
	}
	
	public void setScreenDimensions(int width, int height)
	{
		game.setGaHeight(height);
		game.setGaWidth(width);
	}
	
	public int getScore()
	{
		return game.getScore();
	}
}
