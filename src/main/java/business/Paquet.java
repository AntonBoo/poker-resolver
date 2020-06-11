package business;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import model.COULEUR;
import model.Card;
import model.HAUTEUR;

public class Paquet {
	
	private List<Card> cards = new LinkedList<>();
	
	public Paquet() {
		reset();
	}
	
	
	public Card tirer() {
		Card card = cards.remove(0);
		return card;
	}
	
	public void reset() {
		for (COULEUR couleur : COULEUR.values()) {
			for (HAUTEUR hauteur : HAUTEUR.values()) {
				cards.add(Card.create(hauteur,couleur));
			}
		}
		Collections.shuffle(cards);
	}
	
	public void retirer(Card card) {
		cards.remove(card);
	}
	
	public int restants() {
		return cards.size();
	}

}
