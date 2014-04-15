package net.itca.game.display;

import java.io.IOException;

import net.itca.game.controllers.GameAreaController;
import net.itca.game.elements.GameElement;
import net.itca.game.interfaces.Observer;

import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;

/**
 * 
 * @author Dylan
 * Does all the drawings
 */


public class GameArea extends Form implements Observer
{

	Resources resource;
	Form thisForm = this;
	GameAreaController gameAreaController;
	public GameArea(GameAreaController gac)
	{
		gameAreaController = gac;
		this.setSize(new Dimension(400,400));
		gameAreaController.createPlayer(new Point(this.getWidth()/2,this.getHeight()));
		gameAreaController.setScreenDimensions(this.getWidth(), this.getHeight());
		try
		{
			resource = Resources.open("/theme.res");
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image im = resource.getImage("background.png");
		Label l = new Label(im);
		l.setDraggable(false);
		
		this.addComponent(l);
		this.setDraggable(false);
		
		repaint();
	}
	
	
	
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		requestFocus();
		try 
		{
			resource = Resources.open("/theme.res");
			Image im = resource.getImage("background.png");
			g.drawImage(im, 0, 0, this.getWidth(), this.getHeight());
		} 
		catch (IOException ex)
		{
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
		// Draw player
		g.drawImage(gameAreaController.getPlayer().getElementImage(), gameAreaController.getPlayer().getPosition().getX(), gameAreaController.getPlayer().getPosition().getY());
		
		// Draw other game elements
		synchronized(gameAreaController.getElements())
		{
			for(GameElement ge : gameAreaController.getElements())
			{
				g.drawImage(ge.getElementImage(),ge.getPosition().getX(),ge.getPosition().getY());
			}
		}
		
		// Draw score
		g.drawString(Integer.toString(gameAreaController.getScore()), this.getWidth()/2, 50);
	}
	

	
	public void update()
	{
		repaint();
		// Optimise this by checking the dimensions in the game class 
		gameAreaController.setScreenDimensions(this.getWidth(), this.getHeight());
	}
}
