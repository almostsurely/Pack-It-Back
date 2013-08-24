package packitback.util;

import java.util.ArrayList;
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
		
		//String Builder for running true return.
		StringBuilder builder = new StringBuilder();
		
		//Checks for a null Document.
		if(doc == null){
			return new Validation(false, "Null document.");
		}
		
		//Makes sure Root Element is <PackItBack>
		if(!checkRootElement()){
			return new Validation(false, "Root Element incorrect. Should be <PackItBack>");
		} else {
			builder.append("XML Checks out.\n");
		}
		
		//Makes sure there is at least 1 <Game> tag.
		if(!checkGames()){
			return new Validation(false, "No <Game> tags found!");
		} else {
			builder.append(countGames() + "game(s).\n");
		}
		
		//Makes sure all <Game> tags have a <Name> tag.
		if(!checkGameNames()){
			return new Validation(false, "Not all <Game> tags have a <Name> tag.");
		}
		
		//Makes sure all <Game> tags have at least one <Set> tag.
		if(!checkGameSets()){
			return new Validation(false, "Not all <Game> tags have a <Set> tag.");
		}
		
		//Makes sure all <Set> tags have a <Name> tag.
		if(!checkSetNames()){
			return new Validation(false, "Not all <Set> tags have a <Name> tag.");
		}
		
		//Our "Everything works" return.
		return new Validation(true, builder.toString());
	}
	
	private boolean checkRootElement(){
		return doc.getRootElement().getName().equals("PackItBack");
	}
	
	private boolean checkGames(){
		return countGames() > 0;
	}
	
	private int countGames(){

		int count = 0;
		
		for(Element e : doc.getRootElement().getChildren("Game")){
			count++;
		}
		
		return count;
	}
	
	private boolean checkGameNames(){
		return getGameNames().size() == countGames();
	}
	
	private ArrayList<String> getGameNames(){
		
		ArrayList<String> games = new ArrayList<String>();
		
		for(Element e : doc.getRootElement().getChildren("Game")){
			Element f = e.getChild("Name");
			
			if (f != null){
				games.add(f.getText());
			}
		}
		
		return games;
	}
	
	private boolean checkGameSets(){
		
		//For each Game
		for (Element e : doc.getRootElement().getChildren("Game")){
			List<Element> sets = e.getChildren("Set");
			if(sets.size() >= 1){
				continue;
			} else {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean checkSetNames(){
		//Get List of Games
		List<Element> games = doc.getRootElement().getChildren("Game");
		
		//For each game
		for(Element e : games){
			
			//Get List of sets
			List<Element> sets = e.getChildren("Set");
			
			//For each set
			for(Element f : sets){
				Element n = f.getChild("Name");
				
				if(n == null){
					return false;
				}
			}
		}
		
		return true;
	}
}
