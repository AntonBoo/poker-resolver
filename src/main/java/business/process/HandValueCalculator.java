package business.process;

import static model.RANKING.BRELAN;
import static model.RANKING.CARRE;
import static model.RANKING.DEUX_PAIRES;
import static model.RANKING.FLUSH;
import static model.RANKING.FULL_HOUSE;
import static model.RANKING.STRAIGHT_FLUSH;
import static model.RANKING.SUITE;
import static model.RANKING.PAIRE;
import static model.RANKING.HAUTEUR;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import business.HandValue;
import business.Jeu;
import model.COULEUR;
import model.Card;
import model.HAUTEUR;

public class HandValueCalculator {
	
	private HandValueCalculator() {
	}
	
	public static HandValue process(Jeu jeu) {
		
		List<Card> cartes = jeu.get();
		
		
		int un = cartes.get(0).getHauteur().getRank();
		int deux = cartes.get(1).getHauteur().getRank();
		int trois = cartes.get(2).getHauteur().getRank();
		int quatre = cartes.get(3).getHauteur().getRank();
		int cinq = cartes.get(4).getHauteur().getRank();
		
		int[] classement = new int[5];
		classement[0] = cinq;
		classement[1] = quatre;
		classement[2] = trois;
		classement[3] = deux;
		classement[4] = un;
		
		Map<COULEUR, List<Card>> parCouleur = cartes.stream().collect(Collectors.groupingBy(Card::getCouleur));
		Map<HAUTEUR, List<Card>> parHauteur = cartes.stream().collect(Collectors.groupingBy(Card::getHauteur));
		
		//at least flush
		if (parCouleur.keySet().size() == 1) {

			//23456
			if ((un+1 == deux && deux+1 == trois && trois+1 == quatre && quatre+1 == cinq)) {
				return new HandValue(STRAIGHT_FLUSH, obtenirClassement(cinq, quatre, trois, deux, un));
			}
			else if (isSmallStraight(jeu)) {
				return new HandValue(STRAIGHT_FLUSH, obtenirClassement(quatre, trois, deux, un, cinq));
			}
			//28QKA => AKQ82
			else {
				return new HandValue(FLUSH, obtenirClassement(cinq, quatre, trois, deux, un));
			}
		}
		
		if (parHauteur.keySet().size() == 2) {
			if (parHauteur.values().stream().anyMatch(liste -> liste.size() == 1)) {
				//par exemple : 25555 => 55552
				if (un != deux) {
					return new HandValue(CARRE, obtenirClassement(deux, trois, quatre, cinq, un));
				}
				//par exemple 55559 => 55559
				else {
					return new HandValue(CARRE, obtenirClassement(un, deux, trois, quatre, cinq));
				}

			}
			else {
				//par exemple KKJJJ => JJJKK
				if (un == deux && deux != trois) {
					return new HandValue(FULL_HOUSE, obtenirClassement(trois, quatre, cinq, un, deux));
				}
				else {
					return new HandValue(FULL_HOUSE, obtenirClassement(un, deux, trois, quatre, cinq));
				}
			}
		}
		
		if (parHauteur.keySet().size() == 3) {
			//BRELAN
			if (parHauteur.values().stream().anyMatch(liste -> liste.size() == 3)) {
				//3338T => 333T8
				if  (un == deux && deux == trois) {
					return new HandValue(BRELAN, obtenirClassement(un, deux, trois, cinq, quatre));
				}
				//7999Q => 999Q7
				else if (deux == trois && trois == quatre) {
					
				}
				//45AAA => AAA54
				else {
					return new HandValue(BRELAN, obtenirClassement(trois, quatre, cinq, deux, un));
				}
			}
			//DEUX PAIRES
			else {
				//22338 => 33228
				if  (un == deux && trois == quatre) {
					return new HandValue(DEUX_PAIRES, obtenirClassement(trois, quatre, un, deux, cinq));
				}
				//228JJ => JJ228
				else if (un == deux && quatre == cinq) {
					return new HandValue(DEUX_PAIRES, obtenirClassement(quatre, cinq, un, deux, trois));
				}
				//37799 => 99773
				else {
					return new HandValue(DEUX_PAIRES, obtenirClassement(quatre, cinq, deux, trois, un));
				}
			}
		}
		
		if (parHauteur.keySet().size() == 4) {
			//22789 => 22987
			if  (un == deux) {
				return new HandValue(PAIRE, obtenirClassement(un, deux, cinq, quatre, trois));
			}
			//4JJQK => JJ4QK
			else if (deux == trois) {
				return new HandValue(PAIRE, obtenirClassement(deux, trois, cinq, quatre, un));
			}
			//48QQK => QQK84
			else if (deux == trois) {
				return new HandValue(PAIRE, obtenirClassement(trois, quatre, cinq, deux, un));
			}
			//48JQQ => QQJ74
			else {
				return new HandValue(PAIRE, obtenirClassement(quatre, cinq, trois, deux, un));
			}
		}
		
		//suite
		if (parHauteur.keySet().size() == 5) {
			//23456
			if (un+1 == deux && deux+1 == trois && trois+1 == quatre && quatre+1 == cinq) {
				return new HandValue(SUITE, obtenirClassement(cinq, quatre, trois, deux, un));
			}
			//2345A => 5432A
			else if (isSmallStraight(jeu)) {
				return new HandValue(SUITE, obtenirClassement(quatre, trois, deux, un, cinq));
			}
		}
		
		//hauteur 489TQ => QT984
		return new HandValue(HAUTEUR, obtenirClassement(cinq, quatre, trois, deux, un));
	}
	
	private static boolean isSmallStraight(Jeu jeu) {
		List<Integer> ordre = jeu.get().stream().map(card -> card.getHauteur().getRank()).collect(Collectors.toList());
		Collections.sort(ordre);
		return Arrays.asList(2,3,4,5,14).equals(ordre);
	}
	
	private static int[] obtenirClassement(int a, int b, int c, int d, int e) {
		return new int[] {a,b,c,d,e};
	}

}
