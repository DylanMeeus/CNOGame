package net.itca.game.elements.powerups;

import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;
import com.codename1.ui.util.Resources;

import net.itca.game.elements.MovingGameElement;

/**
 * Shield which falls down - if the player captures it, he gets a shield around himself, allowing him to .
 * @author Dylan
 *
 */
public class Shield extends MovingGameElement
{

	private Resources r;
	private Image im;
	private Point position;
	private Point velocity;
	private int value = 10;
	private boolean isAlive;
	
	
	public Shield(Point startPosition)
	{
		try
		{
		r = Resources.open("/theme.res");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		im = r.getImage("Shield.png");
		setPosition(startPosition);
		velocity = new Point(0,2);
		isAlive = true;
	}
	
	@Override
	public Image getElementImage()
	{
		return this.im;
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
		this.isAlive = aliveParam;
	}

	@Override
	public void setPosition(Point position)
	{
		this.position = position;
	}

}
