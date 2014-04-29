package net.itca.game.display;

import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

public class BaseWindow extends Form
{
	public BaseWindow()
	{
		try
		{
			Resources r = Resources.open("/theme.res");
			Image i = r.getImage("background.png");
			this.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);
			this.setBgImage(i);
		}
		catch(Exception ex)
		{
			
		}
		
		this.setBackCommand(back);
		this.setScrollable(false);
		this.setDraggable(false);
	}
	
	Command back = new Command("back")
	{
		public void actionPerformed(ActionEvent ev)
		{
			MainMenu mm = new MainMenu();
			mm.showBack();
		}
	};
}
