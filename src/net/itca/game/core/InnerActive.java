package net.itca.game.core;

import com.codename1.components.Ads;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BorderLayout;

/**
 * A class for Inner-Active ads in the game.
 * Default position is SOUTH.
 * @author Dylan
 *
 */
public class InnerActive extends Container
{
	

	public InnerActive()
	{	
		try
		{
			Ads ads = new Ads();
			ads.setAppID("Student_2048MemeGame_WP");
			this.addComponent(ads);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	

}
