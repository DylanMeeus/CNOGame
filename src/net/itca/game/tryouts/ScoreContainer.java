package net.itca.game.tryouts;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.xml.XMLParser;
import com.codename1.xml.XMLWriter;

/**
 * Tryout for a container containing the scores.
 * Read from an XML file.
 * @author Dylan
 * 
 */


public class ScoreContainer extends Container
{
	public ScoreContainer()
	{
		this.setLayout(new FlowLayout());
		XMLParser xmlP = new XMLParser();
		XMLWriter xmlW = new XMLWriter(true);
		
		//xmlP.parse(new InputStreamReader(input));
		this.addComponent(new Label("TEST"));
	}
}
