package net.itca.game.elements;

import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;


/**
 * 
 * @author Dylan
 * Class layout for game elements.
 */
public abstract class GameElement
{
	public abstract Image getElementImage();
	public abstract Point getPosition();
	public abstract void setPosition(Point position);
}
