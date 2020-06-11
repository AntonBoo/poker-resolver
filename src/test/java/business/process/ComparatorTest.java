package business.process;

import static model.COULEUR.*;
import static model.HAUTEUR.AS;
import static model.HAUTEUR.DAME;
import static model.HAUTEUR.DIX;
import static model.HAUTEUR.ROI;
import static model.HAUTEUR.*;

import org.junit.Test;

import business.Jeu;
import junit.framework.Assert;
import model.Card;

public class ComparatorTest {
	
	@Test
	public void sameHand() {
		
		Card card1 = Card.create(DIX, COEUR);
		Card card2 = Card.create(VALET, COEUR);
		Card card3 = Card.create(DAME, COEUR);
		Card card4 = Card.create(ROI, COEUR);
		Card card5 = Card.create(AS, COEUR);
		Jeu jeu1 = new Jeu(card2, card1, card3, card4, card5);
		Jeu jeu2 = new Jeu(card3, card5, card2, card4, card1);
		
		Assert.assertEquals(0, Comparator.fight(jeu1, jeu2));
	}
	
	@Test
	public void two_straight() {
		
		Card card1 = Card.create(DIX, COEUR);
		Card card2 = Card.create(VALET, COEUR);
		Card card3 = Card.create(DAME, COEUR);
		Card card4 = Card.create(ROI, COEUR);
		Card card5 = Card.create(AS, COEUR);
		Jeu jeu1 = new Jeu(card2, card1, card3, card4, card5);
		
		Card card6 = Card.create(NEUF, COEUR);
		Card card7 = Card.create(DIX, COEUR);
		Card card8 = Card.create(VALET, COEUR);
		Card card9 = Card.create(DAME, COEUR);
		Card card10 = Card.create(ROI, COEUR);
		Jeu jeu2 = new Jeu(card10, card9, card6, card7, card8);
		
		Assert.assertEquals(1, Comparator.fight(jeu1, jeu2));
	}
	
	
	@Test
	public void two_pairs() {
		
		Card card1 = Card.create(DIX, COEUR);
		Card card2 = Card.create(DIX, PIQUE);
		Card card3 = Card.create(DAME, COEUR);
		Card card4 = Card.create(AS, TREFLE);
		Card card5 = Card.create(AS, COEUR);
		Jeu jeu1 = new Jeu(card2, card1, card3, card4, card5);
		
		Card card6 = Card.create(VALET, COEUR);
		Card card7 = Card.create(DIX, COEUR);
		Card card8 = Card.create(ROI, TREFLE);
		Card card9 = Card.create(ROI, COEUR);
		Card card10 = Card.create(VALET, PIQUE);
		Jeu jeu2 = new Jeu(card10, card9, card6, card7, card8);
		
		Assert.assertEquals(1, Comparator.fight(jeu1, jeu2));
	}
	
	@Test
	public void same_two_pairs() {
		
		Card card1 = Card.create(DIX, COEUR);
		Card card2 = Card.create(DIX, PIQUE);
		Card card3 = Card.create(DAME, COEUR);
		Card card4 = Card.create(DAME, TREFLE);
		Card card5 = Card.create(AS, COEUR);
		Jeu jeu1 = new Jeu(card2, card1, card3, card4, card5);
		
		Card card6 = Card.create(DIX, COEUR);
		Card card7 = Card.create(DIX, PIQUE);
		Card card8 = Card.create(DAME, COEUR);
		Card card9 = Card.create(DAME, TREFLE);
		Card card10 = Card.create(ROI, PIQUE);
		Jeu jeu2 = new Jeu(card10, card9, card6, card7, card8);
		
		Assert.assertEquals(1, Comparator.fight(jeu1, jeu2));
	}
	
	@Test
	public void test_small_straight() {
		
		Card card1 = Card.create(AS, COEUR);
		Card card2 = Card.create(DEUX, PIQUE);
		Card card3 = Card.create(TROIS, COEUR);
		Card card4 = Card.create(CINQ, TREFLE);
		Card card5 = Card.create(QUATRE, COEUR);
		Jeu jeu1 = new Jeu(card2, card1, card3, card4, card5);
		
		Card card6 = Card.create(DEUX, COEUR);
		Card card7 = Card.create(TROIS, PIQUE);
		Card card8 = Card.create(SIX, COEUR);
		Card card9 = Card.create(CINQ, TREFLE);
		Card card10 = Card.create(QUATRE, PIQUE);
		Jeu jeu2 = new Jeu(card10, card9, card6, card7, card8);
		
		Assert.assertEquals(-1, Comparator.fight(jeu1, jeu2));
	}
	
	
	@Test
	public void test_hauteur() {
		
		Card card1 = Card.create(AS, COEUR);
		Card card2 = Card.create(ROI, PIQUE);
		Card card3 = Card.create(TROIS, COEUR);
		Card card4 = Card.create(CINQ, TREFLE);
		Card card5 = Card.create(QUATRE, COEUR);
		Jeu jeu1 = new Jeu(card2, card1, card3, card4, card5);
		
		Card card6 = Card.create(AS, COEUR);
		Card card7 = Card.create(DAME, PIQUE);
		Card card8 = Card.create(SIX, COEUR);
		Card card9 = Card.create(CINQ, TREFLE);
		Card card10 = Card.create(QUATRE, PIQUE);
		Jeu jeu2 = new Jeu(card10, card9, card6, card7, card8);
		
		Assert.assertEquals(1, Comparator.fight(jeu1, jeu2));
	}
	
	
	@Test
	public void one_pairs() {
		
		Card card1 = Card.create(VALET, COEUR);
		Card card2 = Card.create(AS, PIQUE);
		Card card3 = Card.create(DEUX, COEUR);
		Card card4 = Card.create(DAME, TREFLE);
		Card card5 = Card.create(AS, COEUR);
		Jeu jeu1 = new Jeu(card2, card1, card3, card4, card5);
		
		Card card6 = Card.create(VALET, COEUR);
		Card card7 = Card.create(ROI, PIQUE);
		Card card8 = Card.create(DEUX, COEUR);
		Card card9 = Card.create(DAME, TREFLE);
		Card card10 = Card.create(ROI, PIQUE);
		Jeu jeu2 = new Jeu(card10, card9, card6, card7, card8);
		
		Assert.assertEquals(1, Comparator.fight(jeu1, jeu2));
	}


}
