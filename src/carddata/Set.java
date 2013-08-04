package carddata;

import java.util.ArrayList;

public class Set {
	
	private String name;
	private PackDistribution distribution;
	private ArrayList<Card> cards;
	
	public Set(String name, PackDistribution distribution){
		this.name = name;
		this.distribution = distribution;
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
	public PackDistribution getDistribution() {
		return distribution;
	}

	/**
	 * @param distribution the distribution to set
	 */
	public void setDistribution(PackDistribution distribution) {
		this.distribution = distribution;
	}
	
}
