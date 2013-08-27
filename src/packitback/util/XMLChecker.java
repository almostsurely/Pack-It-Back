package packitback.util;

import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

public class XMLChecker {
	
	//FIELDS
	Document doc;
	
	//CONSTRUCTOR
	public XMLChecker(Document doc){
		this.doc = doc;
	}
	
	//METHODS
	public Validation checkDocument(){
		
		//String Builder for running return.
		StringBuilder builder = new StringBuilder();
		boolean valid =true;
		
		//Check for a Null Document
		if(doc == null){
			builder.append("Null Document");
			//If document is null, no further tests can be completed.
			return new Validation(false, builder.toString());
		}
		
		//Make sure Root Tag is <PackItBack>
		Element root = doc.getRootElement();
		
		if(!root.getName().equals("PackItBack")){
			builder.append("Root Element isn't PackItBack. Please make sure you are using the correct XML.");
			//If Root Element is not PackItBack, no further tests can be completed.
			return new Validation(false, builder.toString());
		}
		
		//Check for <Game> tags.
		List<Element> games = root.getChildren("Game");
		
		if(!checkEmpty(games)){
			builder.append("No <Game> tags.");
			//If there are no <Game> tags, no further tests can be completed.
			return new Validation(false, builder.toString());
		}
		
		//Begin iterative stuff.
		
		//For each Game.
		for (Element game : games){
			
			//Check that each <Game> has a <Name>
			if (!checkHasName(game)){
				valid = false;
				builder.append("At least <Game> doesn't have a <Name> \ntag.\n\n");
				continue;
			}
			
			String gameName = game.getChildText("Name");
			
			//Check that each <Game> has a <Set>
			List<Element> sets = game.getChildren("Set");
			if(!checkEmpty(sets)){
				valid = false;
				builder.append(gameName + " doesn't have any sets.\n\n");
				continue;
			}
			
			//For each Set in Game
			for (Element set : sets){
				
				//Check that each <Set> has a <Name>
				if (!checkHasName(set)){
					valid = false;
					builder.append("At least one <Set> in " + gameName + " doesn't have a <Name>.\n\n");
					continue;
				}
				
				String setName = set.getChildText("Name");
				
				//Check that each <Set> has a <Build>
				if(!checkHas(set, "Build")){
					valid = false;
					builder.append(setName + " in " + gameName + " doesn't have any <Build> tags.\n\n");
				} else {
					
					List<Element> builds = set.getChildren("Build");
					
					//For each Build in Set
					for (Element build : builds){
						
						//Check that each <Build> has a <Level>
						if(!checkHas(build, "Level")){
							valid = false;
							builder.append("A <Build> in " + setName + " in " + gameName + " doesn't have a <Level>.\n\n");
						} else {
							
							List<Element> levels = build.getChildren("Level");
							
							//For each Level in Build
							for (Element level : levels){
								if(!checkHasName(level)){
									valid = false;
									builder.append("A <Level> in a <Build> in " + setName + " in " + gameName + " doesn't have a <Name>\n\n");
								}
								
								if(!checkHas(level, "Count")){
									valid = false;
									builder.append("A <Level> in a <Build> in " + setName + " in " + gameName + " doesn't have a <Count>\n\n");
								}
							}
						}
						
						//Check that each <Build> has a <Weight>
						if(!checkHas(build, "Weight")){
							valid = false;
							builder.append("A <Build> in " + setName + " in " + gameName + " doesn't have a <Weight>.\n\n");
						}
					}
				}
				

				
				//Check that each <Set> has a <Card>
				if(!checkHas(set, "Card")){
					valid = false;
					builder.append(setName + " in " + gameName + " doesn't have any <Card> tags.\n\n");
				} else {
					List<Element> cards = set.getChildren("Card");
					
					//For each Card in Set
					for (Element card : cards){
						//Check that each Card has a Name
						if(!checkHasName(card)){
							valid = false;
							builder.append("A <Card> in " + setName + " in " + gameName + " doesn't have a <Name>.\n\n");
						} else if (!checkHas(card, "Level")){
							//If card does have a name, check for Level
							String cardName = card.getChildText("Name");
							
							valid = false;
							builder.append(cardName + " in " + setName + " in " + gameName + " doesn't have a <Level>.\n\n");
						}
					}
				}
			}
		}
		
		//If passes all checks
		if(valid){
			return new Validation(valid, "Yay!");
		} else {
			return new Validation(valid, builder.toString());
		}
	}
	
	private boolean checkEmpty(List<Element> l){
		return l.size() > 0;
	}
	
	private boolean checkHasName(Element e){
		return checkHas(e, "Name");
	}
	
	private boolean checkHas(Element e, String s){
		return e.getChild(s) != null;
	}
}
