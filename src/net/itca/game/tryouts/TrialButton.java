package net.itca.game.tryouts;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.EventDispatcher;

public class TrialButton extends Button
{
	TrialButton tb = this;
	private EventDispatcher dispatcher = new EventDispatcher();
	long delay = 100;
	public TrialButton(String string)
	{
		super.setText(string);
	}

	@Override
	public void pressed()
	{    
		super.pressed();
		Thread t = new Thread(new pressedAction());
		t.start();
	}
	
	/**
	 * 
	 * @param delay
	 * Sets the delay for the firing of the actionEvent while the button is pressed in milliseconds. (default=100ms)
	 */
	public void setDelay(long delay)
	{
		this.delay = delay;
	}
	
	public long getDelay()
	{
		return delay;
	}
	
	@Override
	public void addActionListener(ActionListener l)
	{
		dispatcher.addListener(l);
	}
	
	class pressedAction implements Runnable
	{
		Command cmd = null;
		public void run()
		{
			while(tb.getState()==1)
			{
				try
				{
					fireEvent();
					Thread.sleep(tb.getDelay());
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
		
		public void fireEvent()
		{
	        dispatcher.fireActionEvent(new ActionEvent(tb, -1, -1));
		}
		
	}
	
	
}
