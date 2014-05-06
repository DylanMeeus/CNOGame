package net.itca.components;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.EventDispatcher;


/**
 * Custom button which allows a "WhilePressed" event
 * @author Dylan
 *
 */
public class TrialButton extends Button
{
	private TrialButton tb = this;
	private EventDispatcher dispatcher = new EventDispatcher();
	private long delay = 100; // Default delay
	private boolean WhilePressed = false;
	public TrialButton(String string)
	{
		super.setText(string);
		WhilePressed = true;
	}
	
	public void setWhilePressed(boolean param)
	{
		WhilePressed = param;
	}

	@Override
	public void pressed()
	{    
		super.pressed();
		if(WhilePressed)
		{
			Thread t = new Thread(new pressedAction());
			t.start();
		}
	}
	
	/**
	 * Sets the delay for the firing of the actionEvent while the button is pressed in milliseconds. (default=100ms) 
	 * @param delay
	 */
	public void setDelay(long delay)
	{
		this.delay = delay;
	}
	
	
	/**
	 * Returns the delay between ActionEvents
	 * @return
	 */
	public long getDelay()
	{
		return delay;
	}
	
	@Override
	public void addActionListener(ActionListener l)
	{
		dispatcher.addListener(l);
	}
	
	
	/**
	 * The action to fire while the button is pressed 
	 * @author Dylan
	 *
	 */
	class pressedAction implements Runnable
	{
		Command cmd = null;
		/**
		 * 
		 */
		public void run()
		{
			while(tb.getState()==1) // State 1 = Pressed
			{
				try
				{
					fireEvent();
					Thread.sleep(tb.getDelay()); // Sleep for some delay
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
