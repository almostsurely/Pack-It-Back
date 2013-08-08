package carddata;

import java.util.ArrayList;

public class Pack {
	
	private ArrayList<Build> builds;
	
	public Pack(){
		
	}

	/**
	 * @return the build
	 */
	public ArrayList<Build> getBuilds() {
		return builds;
	}
	
	public void addBuild(Build build){
		builds.add(build);
	}
	
}
