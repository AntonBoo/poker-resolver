package business.process;

import static model.COULEUR.CARREAUX;
import static model.COULEUR.COEUR;
import static model.COULEUR.PIQUE;
import static model.COULEUR.TREFLE;
import static model.HAUTEUR.AS;
import static model.HAUTEUR.CINQ;
import static model.HAUTEUR.DAME;
import static model.HAUTEUR.DEUX;
import static model.HAUTEUR.DIX;
import static model.HAUTEUR.HUIT;
import static model.HAUTEUR.NEUF;
import static model.HAUTEUR.QUATRE;
import static model.HAUTEUR.ROI;
import static model.HAUTEUR.SEPT;
import static model.HAUTEUR.SIX;
import static model.HAUTEUR.TROIS;
import static model.HAUTEUR.VALET;

import org.junit.Assert;
import org.junit.Test;

import business.HandValue;
import business.Jeu;
import model.Card;
import model.RANKING;

public class HandValueCalculatorTest {
	
	@Test
	public void calculer_str8flush() {
		Card card1 = Card.create(DIX, COEUR);
		Card card2 = Card.create(VALET, COEUR);
		Card card3 = Card.create(DAME, COEUR);
		Card card4 = Card.create(ROI, COEUR);
		Card card5 = Card.create(AS, COEUR);
		
		Jeu jeu = new Jeu(card2, card1, card3, card4, card5);
		final HandValue handValue = HandValueCalculator.process(jeu);
		
		Assert.assertEquals(RANKING.STRAIGHT_FLUSH, handValue.getRanking());
		Assert.assertArrayEquals(new int[] {14,13,12,11,10}, handValue.getClassement());
	}
	
	@Test
	public void calculer_small_str8flush() {
		Card card1 = Card.create(AS, COEUR);
		Card card2 = Card.create(TROIS, COEUR);
		Card card3 = Card.create(CINQ, COEUR);
		Card card4 = Card.create(DEUX, COEUR);
		Card card5 = Card.create(QUATRE, COEUR);
		
		final Jeu jeu = new Jeu(card2, card1, card3, card4, card5);
		final HandValue handValue = HandValueCalculator.process(jeu);
		
		Assert.assertEquals(RANKING.STRAIGHT_FLUSH, handValue.getRanking());
		Assert.assertArrayEquals(new int[] {5,4,3,2,14}, handValue.getClassement());
	}
	
	@Test
	public void carre() {
		Card card1 = Card.create(AS, COEUR);
		Card card2 = Card.create(AS, TREFLE);
		Card card3 = Card.create(CINQ, COEUR);
		Card card4 = Card.create(AS, PIQUE);
		Card card5 = Card.create(AS, CARREAUX);
		
		Jeu jeu = new Jeu(card2, card1, card3, card4, card5);
		final HandValue handValue = HandValueCalculator.process(jeu);
		
		Assert.assertEquals(RANKING.CARRE, handValue.getRanking());
		Assert.assertArrayEquals(new int[] {14,14,14,14,5}, handValue.getClassement());
	}
	
	@Test
	public void brelan() {
		Card card1 = Card.create(SEPT, COEUR);
		Card card2 = Card.create(NEUF, TREFLE);
		Card card3 = Card.create(NEUF, COEUR);
		Card card4 = Card.create(NEUF, PIQUE);
		Card card5 = Card.create(DAME, CARREAUX);
		
		Jeu jeu = new Jeu(card2, card1, card3, card4, card5);
		final HandValue handValue = HandValueCalculator.process(jeu);
		
		Assert.assertEquals(RANKING.BRELAN, handValue.getRanking());
		Assert.assertArrayEquals(new int[] {9,9,9,12,7}, handValue.getClassement());
	}
	
	
	@Test
	public void full() {
		Card card1 = Card.create(ROI, COEUR);
		Card card2 = Card.create(ROI, TREFLE);
		Card card3 = Card.create(CINQ, COEUR);
		Card card4 = Card.create(ROI, PIQUE);
		Card card5 = Card.create(CINQ, CARREAUX);
		
		Jeu jeu = new Jeu(card2, card1, card3, card4, card5);
		final HandValue handValue = HandValueCalculator.process(jeu);
		
		Assert.assertEquals(RANKING.FULL_HOUSE, handValue.getRanking());
		Assert.assertArrayEquals(new int[] {13,13,13,5,5}, handValue.getClassement());
	}
	
	@Test
	public void deux_pairs() {
		Card card1 = Card.create(AS, COEUR);
		Card card2 = Card.create(VALET, TREFLE);
		Card card3 = Card.create(CINQ, COEUR);
		Card card4 = Card.create(AS, PIQUE);
		Card card5 = Card.create(CINQ, CARREAUX);
		
		Jeu jeu = new Jeu(card2, card1, card3, card4, card5);
		final HandValue handValue = HandValueCalculator.process(jeu);
		
		Assert.assertEquals(RANKING.DEUX_PAIRES, handValue.getRanking());
		Assert.assertArrayEquals(new int[] {14,14,5,5,11}, handValue.getClassement());
	}
	
	@Test
	public void one_pair() {
		Card card1 = Card.create(AS, COEUR);
		Card card2 = Card.create(VALET, TREFLE);
		Card card3 = Card.create(CINQ, COEUR);
		Card card4 = Card.create(DAME, PIQUE);
		Card card5 = Card.create(CINQ, CARREAUX);
		
		Jeu jeu = new Jeu(card2, card1, card3, card4, card5);
		final HandValue handValue = HandValueCalculator.process(jeu);
		
		Assert.assertEquals(RANKING.PAIRE, handValue.getRanking());
		Assert.assertArrayEquals(new int[] {5,5,14,12,11}, handValue.getClassement());
	}
	
	@Test
	public void straight() {
		Card card1 = Card.create(SIX, COEUR);
		Card card2 = Card.create(HUIT, TREFLE);
		Card card3 = Card.create(NEUF, COEUR);
		Card card4 = Card.create(SEPT, PIQUE);
		Card card5 = Card.create(CINQ, CARREAUX);
		
		Jeu jeu = new Jeu(card2, card1, card3, card4, card5);
		final HandValue handValue = HandValueCalculator.process(jeu);
		
		Assert.assertEquals(RANKING.SUITE, handValue.getRanking());
		Assert.assertArrayEquals(new int[] {9,8,7,6,5}, handValue.getClassement());
	}
	
	@Test
	public void hauteur() {
		Card card1 = Card.create(SIX, COEUR);
		Card card2 = Card.create(HUIT, TREFLE);
		Card card3 = Card.create(DIX, COEUR);
		Card card4 = Card.create(SEPT, PIQUE);
		Card card5 = Card.create(CINQ, CARREAUX);
		
		Jeu jeu = new Jeu(card2, card1, card3, card4, card5);
		final HandValue handValue = HandValueCalculator.process(jeu);
		
		Assert.assertEquals(RANKING.HAUTEUR, handValue.getRanking());
		Assert.assertArrayEquals(new int[] {10,8,7,6,5}, handValue.getClassement());
	}

}
