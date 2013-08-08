package carddata;

import java.util.ArrayList;

public class Set {
	
	private String name;
	private Pack pack;
	private ArrayList<Card> cards;
	
	public Set(String name, Pack pack){
		this.name = name;
		this.pack = pack;
	}
	
	public void addCard(Card card){
		cards.add(card);
	}
	
	public ArrayList<Card> getCards(){
		return cards;
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

	/**
	 * @return the distribution
	 */
	public Pack getPack() {
		return pack;
	}

	/**
	 * @param distribution the distribution to set
	 */
	public void setPack(Pack pack) {
		this.pack = pack;
	}
	
}
