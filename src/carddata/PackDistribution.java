package carddata;

import java.util.ArrayList;

public class PackDistribution {
	
	private ArrayList<Level> levels;
	
	public PackDistribution(){
		
	}
	
	public void addLevel(Level level){
		levels.add(level);
	}
	
	public ArrayList<Level> getDistribution(){
		return levels;
	}
	
}
