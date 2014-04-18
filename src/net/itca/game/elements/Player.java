package net.itca.game.elements;

import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;
import com.codename1.ui.util.Resources;


/**
 * Creates an instance of the player, which can be controlled with
 * the UI buttons. 
 * @author Dylan
 *
 */
public class Player extends GameElement
{
	private Image im;
	private Resources r;
	private Point position;
	public Player(Point startPos)
	{
		try
		{
			r = Resources.open("/theme.res");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		im = r.getImage("Newton.png");
		setPosition(startPos);
	}
	
	
	
	@Override
	public Image getElementImage()
	{
		return im;
	}

	
	@Override
	public void setPosition(Point position)
	{
		this.position = position;
		this.position.setY(this.position.getY()-this.im.getHeight());
	}

	@Override
	public Point getPosition()
	{
		return position;
	}
	
	/**
	 * Moves the player 10px to the left.
	 */
	public void moveLeft()
	{
		position.setX(position.getX()-10);
	}
	
	/**
	 * Moves the player 10px to the right.
	 */
	public void moveRight()
	{
		position.setX(position.getX()+10);
	}

}
