package app;

import static model.COULEUR.CARREAUX;
import static model.COULEUR.COEUR;
import static model.COULEUR.PIQUE;
import static model.COULEUR.TREFLE;
import static model.HAUTEUR.AS;
import static model.HAUTEUR.DAME;
import static model.HAUTEUR.DIX;
import static model.HAUTEUR.ROI;
import static model.HAUTEUR.VALET;

import java.util.Arrays;

import business.Joueur;
import business.process.Simulator;
import model.Card;


public class MainApp {

	public static void main(String[] args) {
		
		Joueur j1 = new Joueur(new Card[] {Card.create(DIX, CARREAUX), Card.create(DIX, COEUR)});
		Joueur j2 = new Joueur(new Card[] {Card.create(AS, TREFLE), Card.create(ROI, CARREAUX)});

		Simulator simulator = new Simulator(j1, j2);
		
		//simulator.matchAllInPreflop();
		
		simulator.matchAllInFlop(Arrays.asList(Card.create(DIX, PIQUE), Card.create(VALET, TREFLE), Card.create(DAME, COEUR)));
		
		simulator.getFinalScore();
	}

}
