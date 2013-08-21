package packitback.util;

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
		
		if(doc == null){
			return new Validation(false, "Null document.");
		}
		
		if(!checkRootElement()){
			return new Validation(false, "Root Element incorrect. Should be <PackItBack>");
		}
		
		if(!checkGames()){
			return new Validation(false, "No <Game> tags found!");
		}
		
		String s = "XML checks out with \n" + countGames() + " game(s).";
		return new Validation(true, s);
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
}
