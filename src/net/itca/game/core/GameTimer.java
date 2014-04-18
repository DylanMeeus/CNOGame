package net.itca.game.core;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import net.itca.game.interfaces.Observable;
import net.itca.game.interfaces.Observer;


/**
 * Timer to control the updating of the game's logic.
 * Updates all elements (Set one step forward in the 'time' of the game (e.g: move objects according to their velocity))
 * @author Dylan
 *
 */
public class GameTimer implements Observable
{
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	public GameTimer()
	{
		System.out.println("test");
		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask()
		{
			public void run()
			{
				notifyObservers();
			}
			
		}, 0, 1000/60);
	
		
	}

	
	public void registerObserver(Observer o)
	{
		observers.add(o);
	}

	public void removeObserver(Observer o)
	{
		observers.remove(o);
	}

	public void notifyObservers()
	{
		synchronized(this)
		{
			for(Observer o : observers)
			{
				o.update();
			}
		}
	}
	
	
}
