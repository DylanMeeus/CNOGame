package net.itca.game.display;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.itca.game.core.InnerActive;
import net.itca.game.display.containers.ScoreContainer;
import net.itca.game.elements.Cloud;
import net.itca.game.factories.GameElementFactory;

import com.codename1.components.Ads;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;


/**
 * Window to be displayed when the game is over.
 * @author Dylan
 *
 */

//TODO: can use template here (one endwindow, one normal highscore window)

public class EndWindow extends BaseWindow
{
	private Container content;
	private Form thisForm = this;
	private String score;
	private ArrayList<Cloud> clouds;
	private int cloudSpawnCycles = 150;
	private int Cycles = 0;
	private Timer timer;
	
	public EndWindow(int score)
	{

		
		super();
		
		clouds = new ArrayList<Cloud>();
		setup();
		this.score = Integer.toString(score);		
		
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask()
		{

			public void run()
			{
				try
				{
					for(Cloud cloud : clouds)
					{
						cloud.move();
					}
					
					Cycles++;
					thisForm.repaint();		
					if(Cycles%cloudSpawnCycles==0)
					{
						spawnCloud();
						Cycles = 0;
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
			}
			
		}, 0, 1000/60); // 60FPS
		
		this.setLayout(new BorderLayout());
		this.addComponent(BorderLayout.CENTER,content);
		this.setBackCommand(back);
		
	}
	
	/**
	 * Spawns a new cloud
	 */
	public void spawnCloud()
	{
		// Perform a cleanup before spawning
		memoryCleanup();
		Random rand = new Random();
		int y = rand.nextInt(this.getHeight()/5);
		int x = 0;
		synchronized(clouds)
		{
			clouds.add(GameElementFactory.createCloud(new Point(x,y)));
		}
	}
	
	/**
	 * Cleans up the objects whom have isAlive set to false.
	 */
	public void memoryCleanup()
	{

		ArrayList<Cloud> newClouds = new ArrayList<Cloud>();
		synchronized(clouds)
		{
			for(Cloud cloud : clouds)
			{
					if(cloud.getPosition().getX() - cloud.getElementImage().getWidth() < this.getWidth())
					{
						newClouds.add(cloud);
					}	
			}
		}
		
		clouds = newClouds;
		newClouds = null;
		
	}
	
	/**
	 * 
	 */
	public void setup()
	{
		content = new Container();
		BorderLayout contentLayout = new BorderLayout();
		contentLayout.setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER);
		content.setLayout(contentLayout);
		Label gameOverlbl = new Label("GAME OVER");
		gameOverlbl.getStyle().setFgColor(0xFF0000);
		content.addComponent(contentLayout.NORTH,new Label("GAME OVER"));
		content.addComponent(contentLayout.CENTER,new ScoreContainer());
		
		
		try
		{
			InnerActive ia = new InnerActive();
			this.addComponent(BorderLayout.SOUTH,ia);
		}
		catch(Exception ex)
		{
			ex.printStackTrace(); 
		}
		
	}
	
	/**
	 * 
	 */
	public void paint(Graphics g)
	{
		super.paint(g);
		super.requestFocus();
		
		//g.setColor(0xFF0000);
		//g.drawString("GAME OVER", this.getWidth()/2, this.getHeight()/2);
		
		if(clouds!=null)
		{
			synchronized(clouds)
			{
				for(Cloud cloud : clouds)
				{
					g.drawImage(cloud.getElementImage(), cloud.getPosition().getX(), cloud.getPosition().getY());
				}
			}
		}
	}
	
	Command back = new Command("back")
	{
		public void actionPerformed(ActionEvent ev)
		{
			synchronized(this)
			{
			timer.cancel();
			MainMenu mm = new MainMenu();
			clouds = null;
			content = null;
			score = null;
			timer = null;
			mm.showBack();
			}
		}
	};
}
