package net.itca.game.elements;

import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;
import com.codename1.ui.util.Resources;

public class Banana extends MovingGameElement
{

	Resources r;
	Image im;
	Point position;
	Point velocity;
	int value = 20;
	boolean isAlive;
	
	public Banana(Point startPosition)
	{
		try
		{
		r = Resources.open("/theme.res");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		im = r.getImage("banana.png");
		setPosition(startPosition);
		velocity = new Point(0,4);
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
