package business.process;

import org.junit.Before;
import org.junit.Test;

import business.Joueur;
import business.Paquet;
import junit.framework.Assert;
import model.Card;

import static model.HAUTEUR.*;
import static model.COULEUR.*;

public class SimulatorTest {
	
	@Before
	public void setUp() {
		Paquet paquet = new Paquet();	
	}
	
	
	@Test
	public void taillePaquet() {
		Card[] cartes1 = new Card[] {Card.create(AS, PIQUE), Card.create(AS, TREFLE)};
		Joueur j1 = new Joueur(cartes1);
		
		Card[] cartes2 = new Card[] {Card.create(AS, CARREAUX), Card.create(ROI, TREFLE)};
		Joueur j2 = new Joueur(cartes2);
		
		Simulator simulator = new Simulator(j1, j2);
		
		Assert.assertEquals(48, simulator.getPaquet().restants());
	}

}
