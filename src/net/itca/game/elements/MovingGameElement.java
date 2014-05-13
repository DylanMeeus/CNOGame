package net.itca.game.elements;

import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;

/**
 * Defines an element of the game which has a velocity and thus moves.
 * @author Dylan
 *
 */
public abstract class MovingGameElement extends GameElement
{

	@Override
	public abstract Image getElementImage();
	
	
	@Override
	public abstract Point getPosition();
	

	/**
	 * Moves the object along with the set velocity
	 */
	public abstract void move();
	
	/**
	 * Returns the points the object is worth upon capturing it.
	 * @return
	 */
	public abstract int getValue();
	
	/**
	 * Returns wether or not the object is alive. Only objects whom are alive are drawn to the screens.
	 * @return
	 */
	public abstract boolean getAlive();
	
	/**
	 * 
	 * @param aliveParam
	 */
	public abstract void setAlive(boolean aliveParam);
}
