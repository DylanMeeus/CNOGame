package net.itca.game.elements;

import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;


/**
 * Defines an element of the game.
 * @author Dylan
 * 
 */
public abstract class GameElement
{
	public abstract Image getElementImage();
	public abstract Point getPosition();
	public abstract void setPosition(Point position);
}
