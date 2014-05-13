package net.itca.game.display.containers;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Vector;
import com.codename1.ui.Container;
import com.codename1.ui.List;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import com.codename1.xml.Element;
import com.codename1.xml.XMLParser;

/**
 * Tryout for a container containing the scores.
 * Read from an XML file.
 * Expect improvement in this file, it is pretty much hardcoded at the moment.
 * @author Dylan
 * 
 */


public class ScoreContainer extends Container
{
	private List list;
	private ArrayList<ArrayList<String>> dictionary; // map <Name><Points>
	private DefaultListModel dlm;
	public ScoreContainer()
	{
		list = new List();
		dictionary = new ArrayList<ArrayList<String>>();
		
		try
		{
			Resources r = Resources.open("/theme.res");
			InputStream is = r.getData("scores.xml");
			XMLParser xmlP = new XMLParser();
			Element e = xmlP.parse(new InputStreamReader(is,"UTF-8")); // e packs all the elements information!
			
			Vector elements = e.getChildrenByTagName("scoreElement");
			for(int i = 0; i < elements.size(); i++)
			{
				Element el = (Element) elements.get(i);
				System.out.println();
				String[] temp = new String[2];
				int position = 0;
				for(int k = 0; k < el.getNumChildren(); k++)
				{
					Element childEl = el.getChildAt(k);
					temp[position] = childEl.getChildAt(0).toString();
					position++;
				}
				ArrayList<String> tempList = new ArrayList<String>();
				tempList.add(temp[0]); tempList.add(temp[1]);
				dictionary.add(tempList);
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		this.setLayout(new FlowLayout());

		
		for(ArrayList<String> x : dictionary)
		{
			list.addItem(x.get(0) + ": " + x.get(1)+" points");
		}
		//xmlP.parse(new InputStreamReader(input));
		list.getStyle().setFgColor(0xFF0000);
		this.addComponent(list);
		
		
	}
}
