package business.util;

import static model.COULEUR.CARREAUX;
import static model.COULEUR.COEUR;
import static model.COULEUR.PIQUE;
import static model.COULEUR.TREFLE;
import static model.HAUTEUR.AS;
import static model.HAUTEUR.DEUX;
import static model.HAUTEUR.DIX;
import static model.HAUTEUR.HUIT;
import static model.HAUTEUR.ROI;

import org.junit.Test;

import business.utils.Combination;
import model.Card;

public class CombinationTest {
	
	
	@Test
	public void test() {
		Combination combi = new Combination();
		
		Card[] cards = new Card[] {
			Card.create(AS, TREFLE),
			Card.create(AS, COEUR),
			Card.create(DIX, TREFLE),
			Card.create(DEUX, COEUR),
			Card.create(ROI, CARREAUX),
			Card.create(HUIT, TREFLE),
			Card.create(AS, PIQUE),
		};
		
		combi.printCombination(cards, 7, 5);
		
		System.out.println("*********** " + combi.getBestHand());
	}

}
