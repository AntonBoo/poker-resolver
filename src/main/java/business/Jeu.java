package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.COULEUR;
import model.Card;
import model.HAUTEUR;

public class Jeu {
	
	public static Integer NOMBRE = 5;
	
	private List<Card> cards = new ArrayList<>();
	private int rankDiversity;
	private int rankMultiplicity;
	
	public Jeu(Card card1, Card card2, Card card3, Card card4, Card card5) {
		cards.add(card1);
		cards.add(card2);
		cards.add(card3);
		cards.add(card4);
		cards.add(card5);
		Collections.sort(cards);
		Map<HAUTEUR,List<Card>> map = cards.stream().collect(Collectors.groupingBy(Card::getHauteur));
		rankDiversity = map.size();
		rankMultiplicity = map.values().stream().mapToInt(liste -> liste.size()).max().getAsInt();
	}
	
	public List<Card> get() {
		return cards;
	}
	
	public boolean isSameSuit() {
		COULEUR firstCouleur = cards.get(0).getCouleur();
		return !cards.stream().map(Card::getCouleur).anyMatch(couleur -> couleur != firstCouleur);
	}
	
	public boolean isSequential() {
		return cards.get(0).getHauteur().getRank()+1 == cards.get(1).getHauteur().getRank()
				&& cards.get(1).getHauteur().getRank()+1 == cards.get(2).getHauteur().getRank()
				&& cards.get(2).getHauteur().getRank()+1 == cards.get(3).getHauteur().getRank()
				&& cards.get(3).getHauteur().getRank()+1 == cards.get(4).getHauteur().getRank();
	}
	
	public boolean isSmallStraight() {
		return cards.get(0).getHauteur().getRank() == 2
				&& cards.get(1).getHauteur().getRank() == 3
				&& cards.get(2).getHauteur().getRank() == 4
				&& cards.get(3).getHauteur().getRank() == 5
				&& cards.get(4).getHauteur().getRank() == 14;
	}
	
	public boolean hasRankDiversity(int number) {
		return rankDiversity == number;
	}
	
	public boolean hasRankMultipliciy(int number) {
		return rankMultiplicity == number;
	}

}
