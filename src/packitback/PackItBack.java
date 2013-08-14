package packitback;

import java.io.IOException;

import org.jdom2.JDOMException;

import packitback.ui.UserInterface;
import packitback.util.DocHandler;

/**
 * This is the main class for PackItBack. It initializes the Document Handler
 * and the User Interface for the program.
 * 
 * @author James Dozier
 */
public class PackItBack {
	
	//FIELDS
	/**
	 *  Fields for the Document Handler and the User Interface.
	 */
	private DocHandler docHand;
	private UserInterface ui;
	
	//CONSTRUCTOR
	/**
	 * Constructor for Pack-It-Back. 
	 * 
	 * @param filename	File path to the .xml document loaded.
	 */
	public PackItBack(String filename){
		
		try {
			docHand = new DocHandler(filename);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initUI();		

	}
	
	//METHODS
	/**
	 * The main method for Pack-It-Back.
	 * 
	 * @param args	One argument accepted as file path. Otherwise defaults to PackItBack.xml in the same directory as the PackItBack.jar.
	 */
	public static void main(String[] args) {
		String filename;
		if(args.length == 0){
			filename = "PackItBack.xml";
		} else {
			filename = args[0];
		}
		new PackItBack(filename);
	}
	
	/**
	 *  Initializes the User Interface, and sends a reference to the Document Handler.
	 */
	private void initUI(){
		ui = new UserInterface(docHand);
		ui.setVisible(true);
	}

}
