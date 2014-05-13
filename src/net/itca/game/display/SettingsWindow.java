package net.itca.game.display;

import com.codename1.io.Preferences;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.spinner.NumericSpinner;

/**
 * Class which displays the settings
 * 
 * @author Dylan
 * 
 */

// Sensitivity Slider?
public class SettingsWindow extends BaseWindow
{
	private Container content;
	NumericSpinner ns;
	private CheckBox touchInputBox;
	public SettingsWindow()
	{
		setup();

		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER);
		this.setLayout(borderLayout);
		this.addComponent(borderLayout.CENTER, content);
	}

	public void setup()
	{
		content = new Container();
		content.setLayout(new GridLayout(4, 2));
		Label touchlabel = new Label("Touch: ");
		touchlabel.getStyle().setFgColor(0xFF0000);
		boolean touchInput = false;
		if (Preferences.get("touchinput", "false").equals("true"))
		{
			touchInput = true;
		}

		touchInputBox = new CheckBox();
		touchInputBox.setSelected(touchInput);
		touchInputBox.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent evt)
			{
				if(touchInputBox.isSelected())
				{
					Preferences.set("touchinput", "true");
				}
				else
				{
					Preferences.set("touchinput","false");
				}
			}

		});
		content.addComponent(touchlabel);
		content.addComponent(touchInputBox);

		// Slider 
		Label senslabel = new Label("Sensitivity:");
		senslabel.getStyle().setFgColor(0xFF0000);
		Slider slider = new Slider();
		slider.setMaxValue(10);
		slider.setProgress(5);
		content.addComponent(senslabel);
		content.addComponent(slider);
	}
}
