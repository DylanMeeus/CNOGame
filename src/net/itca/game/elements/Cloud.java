package net.itca.game.elements;

import java.util.Random;

import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;
import com.codename1.ui.util.Resources;


/**
 * 
 * @author Dylan
 *
 */
public class Cloud extends MovingGameElement
{
	private Resources r;
	private Image im;
	private Point position;
	private Point velocity;
	private int value = 0;
	private boolean isAlive;
	
	public Cloud(Point startPosition)
	{
		try
		{
		r = Resources.open("/theme.res");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		im = r.getImage("white-cloud.png");
		setPosition(startPosition);
		Random rand = new Random();
		int xVel = rand.nextInt(2)+1; // +1 to avoid 0
		velocity = new Point(xVel,0);
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
	public void move()
	{
		position.setX(position.getX()+velocity.getX());
		position.setY(position.getY()+velocity.getY());
	}

	@Override
	public int getValue()
	{
		return value;
	}

	@Override
	public boolean getAlive()
	{
		return isAlive;
	}

	@Override
	public void setAlive(boolean aliveParam)
	{
		isAlive = aliveParam;
	}

	@Override
	public void setPosition(Point position)
	{
		this.position = position;
	}

}
