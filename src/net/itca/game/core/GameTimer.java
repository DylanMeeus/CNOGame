package net.itca.game.core;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import net.itca.game.interfaces.Observable;
import net.itca.game.interfaces.Observer;

public class GameTimer implements Observable
{
	ArrayList<Observer> observers = new ArrayList<Observer>();
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
		for(Observer o : observers)
		{
			o.update();
		}
	}
	
	
}
