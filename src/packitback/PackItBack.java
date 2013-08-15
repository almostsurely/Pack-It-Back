package packitback;

import packitback.ui.UserInterface;

/**
 * This is the main class for PackItBack. It initializes the Document Handler
 * and the User Interface for the program.
 * 
 * @author James Dozier
 */
public class PackItBack {
	
	//FIELDS
	/**
	 *  Fields for the User Interface.
	 */
	private UserInterface ui;
	
	//CONSTRUCTOR
	/**
	 * Constructor for Pack-It-Back. 
	 * 
	 * @param filepath	File path to the .xml document loaded.
	 */
	public PackItBack(String filepath){
		
		initUI(filepath);		

	}
	
	//METHODS
	/**
	 * The main method for Pack-It-Back.
	 * 
	 * @param args	One argument accepted as file path. Otherwise defaults to PackItBack.xml in the same directory as the PackItBack.jar.
	 */
	public static void main(String[] args) {
		String filepath;
		if(args.length == 0){
			filepath = "PackItBack.xml";
		} else {
			filepath = args[0];
		}
		new PackItBack(filepath);
	}
	
	/**
	 *  Initializes the User Interface.
	 *  
	 *  @param filepath File location of the XML
	 */
	private void initUI(String filepath){
		ui = new UserInterface(filepath);
		ui.setVisible(true);
	}

}
