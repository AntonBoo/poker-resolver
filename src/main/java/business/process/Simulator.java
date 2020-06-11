package business.process;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import business.HandValue;
import business.Joueur;
import business.Paquet;
import business.utils.Combination;
import model.Card;

public class Simulator {

	private Paquet paquet = new Paquet();
	private Joueur joueur1;
	private Joueur joueur2;

	private final int n = 100;
	private int nbJ1 = 0;
	private int nbJ2 = 0;

	public Simulator(Joueur j1, Joueur j2) {
		joueur1 = j1;
		joueur2 = j2;
	}

	private void removePlayerCardsFromDeck() {
		Card[] cartes = joueur1.getCartes();
		paquet.retirer(cartes[0]);
		paquet.retirer(cartes[1]);
		cartes = joueur2.getCartes();
		paquet.retirer(cartes[0]);
		paquet.retirer(cartes[1]);
	}

	public void matchAllInPreflop() {
		removePlayerCardsFromDeck();
		long t1 = new Date().getTime();


		


		long duree = new Date().getTime() - t1;
		System.out.println("durée : " + duree);
	}
	
	
	public void matchAllInFlop(List<Card> flopCards) {
		long t1 = new Date().getTime();
		for (int i = 0; i < 10000; i++) {
		new Iteration(flopCards).run();
	}
//		Thread t = new Thread(new Iteration(flopCards));
//		
//		t.start();
		
		
		

//		try {
//			t.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		ExecutorService executor = Executors.newFixedThreadPool(1);
//		for (int i = 0; i < 1000; i++) {
//			executor.execute(new Iteration(flopCards));
//		}
//		executor.shutdown();
//		try {
//			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
//		}
//		catch (InterruptedException e) {
//		}

		long duree = new Date().getTime() - t1;
		System.out.println("durée : " + duree);
	}

	public void getFinalScore() {
		float winJ1 = nbJ1;
		System.out.println("J1 : " + nbJ1 / 100f + "%");
		System.out.println("J2 : " + nbJ2 / 100f + "%");
	}

	private HandValue calculateBestScore(Joueur j, Card card1, Card card2, Card card3, Card card4, Card card5) {
		return new Combination().printCombination(
				new Card[] { j.getCartes()[0], j.getCartes()[1], card1, card2, card3, card4, card5 }, 7, 5);
	}
	
	private HandValue calculateBestScore(List<Card> cartes) {
		return new Combination().printCombination(cartes.stream().toArray(Card[]::new), 7, 5);
	}

	public Paquet getPaquet() {
		return paquet;
	}

	public void setPaquet(Paquet paquet) {
		this.paquet = paquet;
	}

	public Joueur getJoueur1() {
		return joueur1;
	}

	public void setJoueur1(Joueur joueur1) {
		this.joueur1 = joueur1;
	}

	public Joueur getJoueur2() {
		return joueur2;
	}

	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
	}

	class Iteration implements Runnable {
		
		List<Card> flopCards;


		public Iteration(List<Card> flopCards) {
			this.flopCards = flopCards;
		}

		@Override
		public void run() {
			
			Paquet paquet = new Paquet();
			
			for (int i=0; i<1; i++) {
				paquet.retirer(joueur1.getCartes()[0]);
				paquet.retirer(joueur1.getCartes()[1]);
				paquet.retirer(joueur2.getCartes()[0]);
				paquet.retirer(joueur2.getCartes()[1]);
				
				for (Card card : flopCards) {
					paquet.retirer(card);
				}

				int restant = 5 - flopCards.size();
				/*
				 * can be 5 cards (AI prelop) or 2 cards (AI on flop)
				 */
				List<Card> remainingCardsJ1 = IntStream.range(0, restant).mapToObj(j -> paquet.tirer()).collect(Collectors.toList());
				List<Card> remainingCardsJ2 = new ArrayList<>(remainingCardsJ1);
				
				remainingCardsJ1.add(joueur1.getCartes()[0]);
				remainingCardsJ1.add(joueur1.getCartes()[1]);
				remainingCardsJ1.addAll(flopCards);
				
				remainingCardsJ2.add(joueur2.getCartes()[0]);
				remainingCardsJ2.add(joueur2.getCartes()[1]);
				remainingCardsJ2.addAll(flopCards);

				HandValue handJ1 = calculateBestScore(remainingCardsJ1);
				HandValue handJ2 = calculateBestScore(remainingCardsJ2);
				int resultat = Comparator.compare(handJ1, handJ2);
				if (resultat > 0) {
					nbJ1++;
				} else if (resultat < 0) {
					nbJ2++;
				}
				paquet.reset();
			}

		}

	}

}
