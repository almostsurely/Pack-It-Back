package packitback.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class DocHandler {
	
	private Document doc;
	
	public DocHandler(String filename) throws JDOMException, IOException{
		doc = loadFile(filename);
	}

	private Document loadFile(String filename) throws JDOMException, IOException{
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(new File(filename));
		return doc;
	}
	
	public ArrayList<String> getGames(){
		ArrayList<String> games = new ArrayList<String>();
		
		for(Element e : doc.getRootElement().getChildren("Game")){
			Element n = e.getChild("Name");
			games.add(n.getText());
		}
		
		return games;
	}
	
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
	
	public String getPack(String game, String set){
		StringBuilder builder = new StringBuilder();
		
		ArrayList<Element> builds = getBuilds(game, set);
		
		ObjectPicker<Element> picker = new ObjectPicker<Element>();
		
		for (Element e : builds){
			double weight = Double.parseDouble(e.getChild("Weight").getText());
			picker.add(e, weight);
		}
		
		Element packBuild = picker.pickObject();
		
		//TODO Temp jazz
		if(packBuild == null){
			//TODO Tweak or throw NullPointerException
			return "Uh Oh. Something's wrong.";
		}
		
		List<Element> cards = getCards(game, set);
		
		for (Element e : packBuild.getChildren("Level")){
			builder.append(e.getChild("Name").getText() + ":\n");
			picker = new ObjectPicker<Element>();
			
			for (Element f : getCards(game, set)){
				for (Element g : f.getChildren("Level")){
					String a = g.getText();
					String b = e.getChild("Name").getText();
					if (a.equals(b)){
						picker.add(f);
					}
				}
			}
			int count = Integer.parseInt((e.getChild("Count").getText()));
			for (int i = 0; i < count; i++){
				builder.append(picker.pickObject().getChild("Name").getText() + "\n");
			}
		}
		
		return builder.toString();
		
	}
	
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

	private ArrayList<Element> getBuilds(String game, String set){
		ArrayList<Element> builds = new ArrayList<Element>();
		
		for (Element e : doc.getRootElement().getChildren("Game")){
			if (e.getChild("Name").getText().equals(game)){
				for (Element f : e.getChildren("Set")){
					if (f.getChild("Name").getText().equals(set)){
						for (Element g : f.getChild("PackDistribution").getChildren("Build")){
							builds.add(g);
						}
					}
				}
			}
		}
		
		return builds;
	}
}
