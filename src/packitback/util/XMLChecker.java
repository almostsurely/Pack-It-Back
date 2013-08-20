package packitback.util;

import org.jdom2.Document;

public class XMLChecker {
	
	//FIELDS
	Document doc;
	
	//CONSTRUCTOR
	public XMLChecker(Document doc){
		this.doc = doc;
	}
	
	//METHODS
	public String checkDocument(){
		
		if(doc == null){
			return "Null document.";
		}
		
		if(!checkRootElement()){
			return "Root Element incorrect. Should be <PackItBack>";
		}
		
		return "XML checks out!";
	}
	
	private boolean checkRootElement(){
		return doc.getRootElement().getName().equals("PackItBack");
	}
}
