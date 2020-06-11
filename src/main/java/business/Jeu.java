package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Card;

public class Jeu {
	
	public static Integer NOMBRE = 5;
	
	private List<Card> jeu = new ArrayList<>();
	
	public Jeu(Card card1, Card card2, Card card3, Card card4, Card card5) {
		jeu.add(card1);
		jeu.add(card2);
		jeu.add(card3);
		jeu.add(card4);
		jeu.add(card5);
		Collections.sort(jeu);
	}
	
	public List<Card> get() {
		return jeu;
	}

}
