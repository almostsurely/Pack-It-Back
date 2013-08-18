package packitback.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;

/**
 * A handler object for the JDOM Document class. Holds all relevant functions for
 * pulling information from the JDOM Document.
 * 
 * @author James
 *
 */
public class DocHandler {
	
	//FIELDS
	private Document doc;
	
	//CONSTRUCTOR
	/**
	 * Constructs the DocumentHandler.
	 * 
	 * @param filename			String of the file path for the .xml used to load PackItBack
	 * @throws JDOMException	An exception for when something is wrong with the XML.
	 * @throws IOException		An exception for when that pesky file is missing.
	 */
	public DocHandler(String filename) throws JDOMException, IOException{
		doc = loadFile(filename);
	}

	//METHODS
	/**
	 * The method that actually loads the file.
	 * 
	 * All this stuff pretty much the same as the Constructor.
	 * @param filename
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	private Document loadFile(String filename) throws JDOMException, IOException{
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(new File(filename));
		return doc;
	}
	
	/**
	 * @return	An ArrayList of all the games listed in the PackItBack XML.
	 */
	public ArrayList<String> getGames(){
		ArrayList<String> games = new ArrayList<String>();
		
		Element root = doc.getRootElement();
		List<Element> gameE = root.getChildren("Game");
		
		for(Element e : gameE){
			Element n = e.getChild("Name");
			games.add(n.getText());
		}
		
		return games;
	}
	
	/**
	 * A method that takes the param game and returns an ArrayList of all the sets
	 * associated with that game.
	 * 
	 * @param game
	 * @return
	 */
	public ArrayList<String> getSets(String game){
		ArrayList<String> sets = new ArrayList<String>();
		
		for(Element e : doc.getRootElement().getChildren("Game")){
			String eGameName = e.getChild("Name").getText();
			
			if (eGameName == game){
				for(Element f : e.getChildren("Set")){
					Element n = f.getChild("Name");
					sets.add(n.getText());
				}
			}
		}
		
		return sets;
		
	}
	
	/**
	 * This is where the magic happens.
	 * 
	 * @param game						The game the pack comes from.
	 * @param set						The set of that game the pack comes from.
	 * @return							A nicely formatted String of what would be found in the pack
	 * @throws NullPointerException		An Exception when something goes horribly wrong. Most likely a problem with XML formatting.
	 */
	public String getPack(String game, String set) throws NullPointerException{
		
		//Sets up the StringBuilder and ObjectPicker.
		StringBuilder builder = new StringBuilder();
		ObjectPicker<Element> picker = new ObjectPicker<Element>();
		
		//Section of code determines which Pack Build is pulled.
		ArrayList<Element> builds = getBuilds(game, set);
		
		for (Element e : builds){
			double weight = Double.parseDouble(e.getChild("Weight").getText());
			picker.add(e, weight);
		}
		
		Element packBuild = picker.pickObject();
		
		
		//Starts with a List of Levels in the selected build.
		for (Element e : packBuild.getChildren("Level")){
			
			//Adds name of 1st Level to results string.
			builder.append(e.getChild("Name").getText() + ":\n");
			
			//creates a new picker for each Level.
			picker = new ObjectPicker<Element>();
			
			//Adds Card elements to the picker if it's listed as being part of Level.
			for (Element f : getCards(game, set)){
				for (Element g : f.getChildren("Level")){
					String a = g.getText();
					String b = e.getChild("Name").getText();
					if (a.equals(b)){
						picker.add(f);
					}
				}
			}
			
			//Get count of how many cards are in Level.
			int count = Integer.parseInt((e.getChild("Count").getText()));
			
			//Picks a card and appends the Text to the StringBuilder.
			for (int i = 0; i < count; i++){
				Element f = picker.pullObject();
				String s = f.getChild("Name").getText() + "\n";
				builder.append(s);
			}
			
			//Extra Line Break between Levels.
			builder.append("\n");
		}
		
		//Remove last Line Break
		builder.delete(builder.lastIndexOf("\n"), builder.length());
		
		return builder.toString();
		
	}
	
	/**
	 * @param game	The game the cards are from.
	 * @param set	The set in the game the cards are from.
	 * @return		A List of cards to pick from.
	 */
	private List<Element> getCards(String game, String set) {
		
		for (Element e : doc.getRootElement().getChildren("Game")){
			if (e.getChild("Name").getText().equals(game)){
				for (Element f : e.getChildren("Set")){
					if (f.getChild("Name").getText().equals(set)){
						return f.getChildren("Card");
					}
				}
			}
		}
		
		return null;
	}

	/**
	 * Builds are basically different ways a pack's levels can be arranged.
	 * 
	 * @param game	The game the builds are from
	 * @param set	The set in the game the builds are from.
	 * @return		A List of the pack builds possible in that set.
	 */
	private ArrayList<Element> getBuilds(String game, String set){
		ArrayList<Element> builds = new ArrayList<Element>();
		
		for (Element e : doc.getRootElement().getChildren("Game")){
			if (e.getChild("Name").getText().equals(game)){
				for (Element f : e.getChildren("Set")){
					if (f.getChild("Name").getText().equals(set)){
						for (Element g : f.getChildren("Build")){
							builds.add(g);
						}
					}
				}
			}
		}
		
		return builds;
	}
}
