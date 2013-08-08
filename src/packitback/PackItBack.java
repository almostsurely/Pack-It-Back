package packitback;

import java.io.IOException;

import org.jdom2.JDOMException;

import packitback.util.DocHandler;

public class PackItBack {
	
	private DocHandler docHand;
	
	public PackItBack(){
		// TODO Auto-generated method stub
		try {
			docHand = new DocHandler("PackItBack.xml");
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//TODO Remove
		System.out.println(docHand.getPack("Zatch Bell", "Premier Set"));
		
	}
	
	public static void main(String[] args) {
		new PackItBack();
	}

}
