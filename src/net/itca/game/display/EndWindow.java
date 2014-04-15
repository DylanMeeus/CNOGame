package net.itca.game.display;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.itca.game.elements.Cloud;
import net.itca.game.factories.GameElementFactory;

import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Point;
import com.codename1.ui.plaf.Style;

public class EndWindow extends Form
{
	Container content;
	Form thisForm = this;
	String score;
	ArrayList<Cloud> clouds;
	int cloudSpawnCycles = 150;
	int Cycles = 0;
	Timer timer;
	public EndWindow(int score)
	{
		// Setting a radial background with gradiant from white -> blue
		this.getStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
		this.getStyle().setBackgroundGradientStartColor(0x0000CC);
		this.getStyle().setBackgroundGradientEndColor(0x0000AA);
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
		
		// set the back command to open the main menu
		
		this.setBackCommand(back);
	}
	
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
	
	public void setup()
	{
		content = new Container();
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		super.requestFocus();
		g.drawString("GAME", 10, 20);
		g.drawString("OVER", 20, 50);
		
		synchronized(clouds)
		{
			for(Cloud cloud : clouds)
			{
				g.drawImage(cloud.getElementImage(), cloud.getPosition().getX(), cloud.getPosition().getY());
			}
		}
	}
	
	Command back = new Command("back")
	{
		public void actionPerformed(ActionEvent ev)
		{
			timer.cancel();
			timer = null;
			MainMenu mm = new MainMenu();
			mm.showBack();
		}
	};
}
