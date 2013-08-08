package carddata;

import java.util.ArrayList;

public class Card {
	
	private String name;
	private ArrayList<String> levels;
	
	public Card(String name){
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public void addLevel(String level){
		levels.add(level);
	}
	
	public ArrayList<String> getLevels(){
		return levels;
	}
	
}
