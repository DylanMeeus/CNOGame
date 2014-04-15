package net.itca.game.display;

import net.itca.game.controllers.GameAreaController;
import net.itca.game.core.Game;
import net.itca.game.core.GameTimer;
import net.itca.game.tryouts.TrialButton;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;


/**
 * 
 * @author Dylan
 * The main window with all game components
 */
public class GameWindow extends Form
{

	GameArea ga;
	Container content;
	GameTimer timer;
	TrialButton left,right;
	Game game;
	GameAreaController gameAreaController;
	public GameWindow(Game g)
	{
		game = g;
		this.setBackCommand(back);
		setup();
		this.setDraggable(false);
		BorderLayout b = new BorderLayout();
		b.setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER);
		this.setLayout(b);		
		this.addComponent(b.CENTER,content);
	}
	
	public void setup()
	{
		content = new Container();
		gameAreaController = new GameAreaController(game);
		ga = new GameArea(gameAreaController);
		timer = new GameTimer();
		timer.registerObserver(ga);
		timer.registerObserver(game);
		BorderLayout b = new BorderLayout();
		b.setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER);
		content.setLayout(b);
		content.addComponent(b.CENTER,ga);
		
		
		// Button panel
		Container buttonContainer = new Container();
		buttonContainer.setLayout(new GridLayout(1,2));
		left = new TrialButton("<=");
		left.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent evt)
			{	
				gameAreaController.movePlayerLeft();	
			}
			
		});
		left.setDelay(25);
		right = new TrialButton("=>");
		right.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent evt)
			{
				gameAreaController.movePlayerRight();
			}
			
		});
		right.setDelay(25);
		buttonContainer.addComponent(left);
		buttonContainer.addComponent(right);
		content.addComponent(b.SOUTH,buttonContainer);
	}
	
	Command back = new Command("Back")
	{
		public void actionPerformed(ActionEvent ev)
		{
			// Show popup to ask if they are sure!
			MainMenu mm = new MainMenu();
			timer.removeObserver(game);
			mm.showBack();
		}
	};
}
