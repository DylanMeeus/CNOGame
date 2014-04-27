package com.mycompany.myapp;

import net.itca.game.display.MainMenu;

import com.codename1.io.Log;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Display;
import com.codename1.ui.Form;

import userclasses.StateMachine;


//TODO: Use device.getDisplay() to set the width/height of windows.
public class MyApplication
{

	private Form current;

	public void init(Object context)
	{
	
	}

	public void start()
	{
		if (current != null)
		{
			current.show();
			return;
		}
		new StateMachine("/theme");
		MainMenu m = new MainMenu();
		m.show();
	}

	public void stop()
	{
		current = Display.getInstance().getCurrent();
	}

	public void destroy()
	{
	}
}
