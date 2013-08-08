package carddata;

import java.util.ArrayList;

public class Build {
	
	private ArrayList<Level> levels;
	private int weight;
	
	public Build(int weight){
		this.weight = weight;
	}
	
	public void addLevel(Level level){
		levels.add(level);
	}
	
	public ArrayList<Level> getLevels(){
		return levels;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
}
