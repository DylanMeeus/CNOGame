package net.itca.game.display;

import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 * Displays the about section of the game
 * @author Dylan
 *
 */
public class AboutWindow extends BaseWindow
{
	private Container content;
	public AboutWindow()
	{
		super();
		setup();
		this.setLayout(new BorderLayout());
		this.addComponent(BorderLayout.CENTER,content);
		
	}
	
	/**
	 * Setup instantiates the window's componnents
	 */
	public void setup()
	{
		content = new Container();
		content.setLayout(new GridLayout(4,1));

		Label me = new Label("Coded by : Dylan Meeus | meeusdylan@hotmail.com");
		me.getStyle().setFgColor(0xFF0000);
		
		content.addComponent(me);
		Label sean = new Label("Design by: Sean McGee");
		sean.getStyle().setFgColor(0xFF0000);
		content.addComponent(sean);
		Label source = new Label("OpenSource and available at: github.com/CNOGame");
		source.getStyle().setFgColor(0xFF0000);
		content.addComponent(source);
		
		
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
