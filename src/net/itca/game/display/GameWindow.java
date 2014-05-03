package net.itca.game.display;

import net.itca.components.TrialButton;
import net.itca.game.controllers.GameAreaController;
import net.itca.game.core.Game;
import net.itca.game.core.GameTimer;
import net.itca.game.interfaces.Observer;

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
 * @author Dylan The main window with all game components, this class also
 *         listens to the game to find out when the game's over.
 */
public class GameWindow extends Form implements Observer
{
	private GameArea ga;
	private Container content;
	private GameTimer timer;
	private TrialButton left, right;
	private Game game;
	private GameAreaController gameAreaController;

	public GameWindow(Game g)
	{
		game = g;
		game.registerObserver(this);
		this.setBackCommand(back);
		BorderLayout b = new BorderLayout();
		b.setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER);
		this.setLayout(b);
		setup();
		this.setDraggable(false);

		this.addComponent(b.SOUTH, content);

		this.setScrollable(false);
		content.setScrollable(false);
		content.setDraggable(false);

		this.addPointerDraggedListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				// gameAreaController.movePlayerLeft();
				// System.out.println(evt.getX());
				// System.out.print(" y: " + evt.getY());
			}
		});
	}

	/**
	 * Setup of the gamewindow's content container. Get's called by the
	 * constructor.
	 */
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
		this.addComponent(b.CENTER, ga);

		// Button panel
		Container buttonContainer = new Container();
		buttonContainer.setLayout(new GridLayout(1, 2));
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
		content.addComponent(b.SOUTH, buttonContainer);
	}

	Command back = new Command("Back")
	{
		// We need to show an end form before exiting the game - microsoft store
		// rules
		// We need to ask the user if he's sure he wants to quit.
		public void actionPerformed(ActionEvent ev)
		{
			// Show popup to ask if they are sure!
			MainMenu mm = new MainMenu();
			timer.removeObserver(game);
			mm.showBack();
		}
	};

	public void update()
	{
		// If the game is over, start the endgame sequence
		if (game.getGameOver())
		{
			synchronized (this)
			{
				System.out.println("Testing the gameOver method");
				timer.removeObserver(game);
				timer = null;
				game.removeObserver(this);
				EndWindow ew = new EndWindow(game.getScore());
				game = null; // remove a reference to the game, thus all other
								// objects on the heap related to it.
				ew.show();
			}
		}
	}

	/*
	 * Everything undernearth this line is "tryout" code. Might break the game.
	 */
	// Do not use PointerPressed == That's to register events on the buttons!
	
	public void pointerDragged(int x, int y)
	{
		System.out.println("This shouldn't do anything");
	}
	
	public void pointerDragged(int[] x, int[] y) 
	{
		System.out.println("This shouldn't do anything either");
		if(x.length>1)
		{
			if(x[0] > x[1])
			{
				gameAreaController.movePlayerRight();
			}
		}
	}
	
}
