package packitback.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jdom2.JDOMException;

import packitback.util.DocHandler;
import packitback.util.Validation;

/**
 * The UserInterface Class. Sets up the User Interface and overall functionality.
 * 
 * @author James
 *
 */
public class UserInterface extends JFrame{

	//FIELDS
	private static final long serialVersionUID = 6202616830881402019L;
	
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 300;
	private static final int MARGIN = 5;
	private static final int COMPONENT_HEIGHT = 25;
	private static final int BUTTON_WIDTH = 100;
	private static final int BUTTON_Y = 225;
	
	private JTextArea results = new JTextArea(1, 20);
	private JComboBox<String> games;
	private JComboBox<String> sets;
	private DocHandler docHand;
	Validation val;
	
	//CONSTRUCTOR
	/**
	 * Constructor for the User Interface.
	 * 
	 * @param filepath Location of the XML
	 */
	public UserInterface(String filepath){

		init();
		
		try {
			this.docHand = new DocHandler(filepath);
			val = docHand.checkDocument();
			results.setText(val.getValidationText());
			if(val.isValidated()){
				refreshGames();
				refreshSets();
			}
		} catch (JDOMException | IOException e) {
			results.setText(e.toString());
		}
		
		
	}
	
	//METHODS
	/**
	 *  Initializes the User Interface and sets proper settings.
	 */
	private void init(){
		setTitle("Pack-It-Back");
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 2));
		
		//UI Panel
		JPanel uiPanel = new JPanel();
		uiPanel.setLayout(null);
		
		//Setting up components
		JLabel gameLabel = new JLabel("Games");
		gameLabel.setBounds(MARGIN, MARGIN, (WINDOW_WIDTH/2)-(MARGIN * 2), COMPONENT_HEIGHT);
		
		games = new JComboBox<String>();
		games.setBounds(MARGIN, (MARGIN * 2) + COMPONENT_HEIGHT, (WINDOW_WIDTH/2)-(MARGIN * 2), COMPONENT_HEIGHT);
		games.addActionListener(new ComboListener());
		
		JLabel setsLabel = new JLabel("Sets");
		setsLabel.setBounds(MARGIN, (MARGIN * 3) + (COMPONENT_HEIGHT * 2), (WINDOW_WIDTH/2)-(MARGIN * 2), COMPONENT_HEIGHT);
		
		sets = new JComboBox<String>();
		sets.setBounds(MARGIN, (MARGIN * 4) + (COMPONENT_HEIGHT * 3), (WINDOW_WIDTH/2)-(MARGIN * 2), COMPONENT_HEIGHT);
		
		JButton pullButton = new JButton("Pull Pack");
		pullButton.addActionListener(new ButtonListener());
		pullButton.setBounds((WINDOW_WIDTH/2) - MARGIN - BUTTON_WIDTH, BUTTON_Y, BUTTON_WIDTH, COMPONENT_HEIGHT);
		
		//Adding the components
		uiPanel.add(gameLabel);
		uiPanel.add(games);
		uiPanel.add(setsLabel);
		uiPanel.add(sets);
		uiPanel.add(pullButton);

		mainPanel.add(uiPanel);
	
		//Results Pane
		results.setEditable(false);
		results.setLineWrap(true);
		JScrollPane resultsPane = new JScrollPane(results);
		mainPanel.add(resultsPane);
		
		
		add(mainPanel, BorderLayout.CENTER);
	}

	/**
	 * This method loads the list of games from the main XML into the games JCombobox.
	 */
	private void refreshGames() {
		games.removeAllItems();
		
		for (String s : docHand.getGames()){
			games.addItem(s);
		}
		
	}
	
	/**
	 * This method resets the options in the sets JComboBox to reflect Sets relevant to the game in the games JComboBox.
	 */
	private void refreshSets() {
		sets.removeAllItems();
		
		for (String s : docHand.getSets((String)games.getSelectedItem())){
			sets.addItem(s);
		}
	}
	
	/**
	 * Listener for the "Pull Pack" JButton
	 * On press it fills the results JTextbox with a String of the pulled cards.
	 */
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(val.isValidated()){
				String resultsString = null;
				try {
					resultsString = docHand.getPack((String)games.getSelectedItem(), (String)sets.getSelectedItem());
				} catch (NullPointerException e1) {
					// TODO Auto-generated catch block
					resultsString = e1.toString();
				}
				results.setText(resultsString);
			}
		}
		
	}
	
	/**
	 * Listener for when the games JComboButton changes.
	 * Forces a refresh for the sets JComboButton.
	 */
	private class ComboListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(val.isValidated()){
				refreshSets();
			}
		}
		
	}
}
