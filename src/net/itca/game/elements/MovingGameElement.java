package net.itca.game.elements;

import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;

public abstract class MovingGameElement extends GameElement
{

	@Override
	public abstract Image getElementImage();
	@Override
	public abstract Point getPosition();
	
	public abstract Point getVelocity(); // Point so we can add sideways elements later
	
	public abstract void move();
	
	public abstract int getValue();
	
	public abstract boolean getAlive();
	
	public abstract void setAlive(boolean aliveParam);
}
