package net.itca.game.display;

import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;

public class AboutWindow extends Form
{
	Container content;
	public AboutWindow()
	{
		setup();
		this.setBackCommand(back);
		this.addComponent(content);
	}
	
	public void setup()
	{
		content = new Container();
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
