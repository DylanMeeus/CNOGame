package net.itca.game.elements;

import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;
import com.codename1.ui.util.Resources;

public class Bomb extends MovingGameElement
{

	Resources r;
	Image im;
	Point position;
	Point velocity;
	int value = -1; // Value for BOMB
	boolean isAlive;
	
	public Bomb(Point startPos)
	{
		try
		{
		r = Resources.open("/theme.res");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		im = r.getImage("bomb.png");
		setPosition(startPos);
		velocity = new Point(0,8);
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
