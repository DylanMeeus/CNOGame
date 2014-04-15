package net.itca.game.elements;

import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;
import com.codename1.ui.util.Resources;

public class Player extends GameElement
{

	Image im;
	Resources r;
	Point position;
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
	
	public void moveLeft()
	{
		position.setX(position.getX()-10);
	}
	
	public void moveRight()
	{
		position.setX(position.getX()+10);
	}

}
