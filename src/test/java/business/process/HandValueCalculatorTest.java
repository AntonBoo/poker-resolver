package business.process;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import business.Jeu;
import business.Paquet;
import model.Card;
import model.RANKING;

import static model.COULEUR.*;
import static model.HAUTEUR.*;

public class HandValueCalculatorTest {
	
	@Test
	public void calculer_str8flush() {
		Card card1 = Card.create(DIX, COEUR);
		Card card2 = Card.create(VALET, COEUR);
		Card card3 = Card.create(DAME, COEUR);
		Card card4 = Card.create(ROI, COEUR);
		Card card5 = Card.create(AS, COEUR);
		
		Jeu jeu = new Jeu(card2, card1, card3, card4, card5);
		
		
		Assert.assertEquals(RANKING.STRAIGHT_FLUSH, HandValueCalculator.process(jeu).getRanking());
	}
	
	@Test
	public void calculer_small_str8flush() {
		Card card1 = Card.create(AS, COEUR);
		Card card2 = Card.create(TROIS, COEUR);
		Card card3 = Card.create(CINQ, COEUR);
		Card card4 = Card.create(DEUX, COEUR);
		Card card5 = Card.create(QUATRE, COEUR);
		
		Jeu jeu = new Jeu(card2, card1, card3, card4, card5);
		
		Assert.assertEquals(RANKING.STRAIGHT_FLUSH, HandValueCalculator.process(jeu).getRanking());
	}
	
	@Test
	public void carre() {
		Card card1 = Card.create(AS, COEUR);
		Card card2 = Card.create(AS, TREFLE);
		Card card3 = Card.create(CINQ, COEUR);
		Card card4 = Card.create(AS, PIQUE);
		Card card5 = Card.create(AS, CARREAUX);
		
		Jeu jeu = new Jeu(card2, card1, card3, card4, card5);
		
		Assert.assertEquals(RANKING.CARRE, HandValueCalculator.process(jeu).getRanking());
	}
	
	@Test
	public void brelan() {
		Card card1 = Card.create(AS, COEUR);
		Card card2 = Card.create(AS, TREFLE);
		Card card3 = Card.create(CINQ, COEUR);
		Card card4 = Card.create(AS, PIQUE);
		Card card5 = Card.create(SIX, CARREAUX);
		
		Jeu jeu = new Jeu(card2, card1, card3, card4, card5);
		
		Assert.assertEquals(RANKING.BRELAN, HandValueCalculator.process(jeu).getRanking());
	}
	
	
	@Test
	public void full() {
		Card card1 = Card.create(AS, COEUR);
		Card card2 = Card.create(AS, TREFLE);
		Card card3 = Card.create(CINQ, COEUR);
		Card card4 = Card.create(AS, PIQUE);
		Card card5 = Card.create(CINQ, CARREAUX);
		
		Jeu jeu = new Jeu(card2, card1, card3, card4, card5);
		
		Assert.assertEquals(RANKING.FULL_HOUSE, HandValueCalculator.process(jeu).getRanking());
	}
	
	@Test
	public void deux_pairs() {
		Card card1 = Card.create(AS, COEUR);
		Card card2 = Card.create(VALET, TREFLE);
		Card card3 = Card.create(CINQ, COEUR);
		Card card4 = Card.create(AS, PIQUE);
		Card card5 = Card.create(CINQ, CARREAUX);
		
		Jeu jeu = new Jeu(card2, card1, card3, card4, card5);
		
		Assert.assertEquals(RANKING.DEUX_PAIRES, HandValueCalculator.process(jeu).getRanking());
	}
	
	@Test
	public void one_pair() {
		Card card1 = Card.create(AS, COEUR);
		Card card2 = Card.create(VALET, TREFLE);
		Card card3 = Card.create(CINQ, COEUR);
		Card card4 = Card.create(DAME, PIQUE);
		Card card5 = Card.create(CINQ, CARREAUX);
		
		Jeu jeu = new Jeu(card2, card1, card3, card4, card5);
		
		Assert.assertEquals(RANKING.PAIRE, HandValueCalculator.process(jeu).getRanking());
	}
	
	@Test
	public void straight() {
		Card card1 = Card.create(SIX, COEUR);
		Card card2 = Card.create(HUIT, TREFLE);
		Card card3 = Card.create(NEUF, COEUR);
		Card card4 = Card.create(SEPT, PIQUE);
		Card card5 = Card.create(CINQ, CARREAUX);
		
		Jeu jeu = new Jeu(card2, card1, card3, card4, card5);
		
		Assert.assertEquals(RANKING.SUITE, HandValueCalculator.process(jeu).getRanking());
	}
	
	@Test
	public void hauteur() {
		Card card1 = Card.create(SIX, COEUR);
		Card card2 = Card.create(HUIT, TREFLE);
		Card card3 = Card.create(DIX, COEUR);
		Card card4 = Card.create(SEPT, PIQUE);
		Card card5 = Card.create(CINQ, CARREAUX);
		
		Jeu jeu = new Jeu(card2, card1, card3, card4, card5);
		
		Assert.assertEquals(RANKING.HAUTEUR, HandValueCalculator.process(jeu).getRanking());
	}

}
