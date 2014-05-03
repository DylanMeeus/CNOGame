package net.itca.game.display;

import java.io.IOException;

import net.itca.game.controllers.GameAreaController;
import net.itca.game.elements.GameElement;
import net.itca.game.interfaces.Observer;

import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;



/**
 * Controls the drawing of elements to the screen. 
 * @author Dylan
 */
public class GameArea extends Form implements Observer
{

	private Resources resource;
	private Form thisForm = this;
	private GameAreaController gameAreaController;
	
	public GameArea(GameAreaController gac)
	{
		gameAreaController = gac;
		Display display = Display.getInstance();
		this.setSize(new Dimension(400,400)); // TODO: Dynamic dimensions!
		gameAreaController.createPlayer(new Point(this.getWidth()/2,this.getHeight()));
		gameAreaController.setScreenDimensions(this.getWidth(), this.getHeight());
		try
		{
			resource = Resources.open("/theme.res");
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Image im = resource.getImage("background.png");
		Image im2 = resource.getImage("slowmotionOverlay.png");
		Label l = new Label(im2);

		l.setDraggable(false);
		l.setVisible(false);
		this.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
		this.setBgImage(im);
		this.addComponent(l);
		this.setDraggable(false);
		this.setScrollable(false);
		repaint();
		
	}
	
	
	
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		requestFocus();

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
		g.setColor(0xFFFFFF);
		g.drawString(Integer.toString(gameAreaController.getScore()), this.getWidth()/2, 50);
	}
	

	
	public void update()
	{
		repaint();
		// Optimise this by checking the dimensions in the game class 
		gameAreaController.setScreenDimensions(this.getWidth(), this.getHeight());
	}
}
