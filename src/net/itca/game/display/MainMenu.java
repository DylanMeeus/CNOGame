package net.itca.game.display;

import net.itca.game.core.Game;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;


/**
 * Main menu of the game.
 * @author Dylan
 *
 */
public class MainMenu extends Form
{
	private Container content;
	private Button Start, About, Settings, Info;
	public MainMenu()
	{
		setup();
		this.setLayout(new BorderLayout());
		this.addComponent(BorderLayout.CENTER,content);
	
		try
		{
		Resources r = Resources.open("/theme.res");
		Image im = r.getImage("background.png");
		this.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
		this.setBgImage(im);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 */
	public void setup()
	{
		content = new Container();
		content.setLayout(new GridLayout(4,2));
		Start = new Button("Start");
		Start.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent evt)
			{
				Game game = new Game();
				GameWindow gw = new GameWindow(game);
				gw.show();
			}
			
		});
		content.addComponent(Start);
		
		Info = new Button("Game info");
		Info.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent evt)
			{
				InfoWindow iw = new InfoWindow();
				iw.show();
			}
			
		});
		content.addComponent(Info);
		
		

		Settings = new Button("Settings");
		Settings.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				
			}
		});
		content.addComponent(Settings);
		
		
		
		About = new Button("About");
		About.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent evt)
			{
				AboutWindow ab = new AboutWindow();
				ab.show();
			}
			
		});
		content.addComponent(About);
	
		
	}
}
