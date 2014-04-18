package net.itca.game.elements.fruit;

import net.itca.game.elements.MovingGameElement;

import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;
import com.codename1.ui.util.Resources;

/**
 * 
 * @author Dylan
 *
 */
public class Apple extends MovingGameElement
{
	private Resources r;
	private Image im;
	private Point position;
	private Point velocity;
	private int value = 10;
	private boolean isAlive;
	
	public Apple(Point startPosition)
	{
		try
		{
		r = Resources.open("/theme.res");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		im = r.getImage("apple.png");
		setPosition(startPosition);
		velocity = new Point(0,2);
		isAlive = true;
	}
	
	@Override
	public Image getElementImage()
	{
		return im;
	}
	
	@Override
	public Point getPosition()
	{
		return position;
	}
	
	@Override
	public void setPosition(Point position)
	{
		this.position = position;
	}
	
	@Override
	public Point getVelocity()
	{
		return velocity;
	}
	
	@Override
	public void move()
	{
		position.setX(position.getX()+velocity.getX());
		position.setY(position.getY()+velocity.getY());
	}
	
	
	public int getValue()
	{
		return this.value;
	}
	
	public boolean getAlive()
	{
		return isAlive;
	}
	
	@Override
	public void setAlive(boolean aliveParam)
	{
		isAlive = aliveParam;
	}

}
