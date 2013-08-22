package packitback.util;

import java.util.ArrayList;

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
		} else {
			
			builder.append("Games:\n");
			
			for(String s : getGameNames()){
				builder.append(s + ", ");
			}
			
			builder.append("\n");
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
}
