package carddata;

import java.util.ArrayList;

public class Game {
	
	private ArrayList<Set> sets;
	private String name;
	
	public Game(String name){
		this.name = name;
	}
	
	public void addSet(Set set){
		sets.add(set);
	}
	
	public ArrayList<Set> getSets(){
		return sets;
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
	
	
}
