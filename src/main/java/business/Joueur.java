package business;

import model.Card;

public class Joueur {
	
	private Card[] cartes;
	
	public Joueur(Card[] cartes) {
		this.cartes = cartes;
	}

	public Card[] getCartes() {
		return cartes;
	}

}
