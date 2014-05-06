package net.itca.game.display;

import net.itca.game.core.Game;
import net.itca.game.core.GameMode;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;

public class SelectModeWindow extends BaseWindow
{
	Container content;
	Button Arcade, Timed;
	public SelectModeWindow()
	{
		super();
		setup();
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER);
		this.setLayout(borderLayout);
		this.addComponent(borderLayout.CENTER,content);
		
		Container northCentered = new Container();
		Label gamemode = new Label("GAMEMODE");
		gamemode.getStyle().setFgColor(0xFF0000);
		BorderLayout northLayout = new BorderLayout();
		northLayout.setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER);
		northCentered.setLayout(northLayout);
		northCentered.addComponent(northLayout.CENTER,gamemode);
		this.addComponent(borderLayout.NORTH,northCentered);
	}
	
	public void setup()
	{
		content = new Container();
		content.setLayout(new GridLayout(2,1));
		
		Arcade = new Button("ARCADE");
		Timed = new Button("TIMED");
		Arcade.getStyle().setFgColor(0xFF0000);
		Arcade.addActionListener(new ModeListener());
		Timed.addActionListener(new ModeListener());
		Timed.getStyle().setFgColor(0xFF0000);
		content.addComponent(Arcade);
		content.addComponent(Timed);
	}
	
	class ModeListener implements ActionListener
	{

		public void actionPerformed(ActionEvent evt)
		{
			Game g = null;
			if(evt.getSource()==Arcade)
			{
				g = new Game(GameMode.ARCADE);
			}
			else
			{
				g = new Game(GameMode.TIMED);
			}
			GameWindow gw = new GameWindow(g);
			gw.show();
		}

	}
}
