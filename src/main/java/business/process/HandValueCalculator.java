package business.process;

import static model.RANKING.BRELAN;
import static model.RANKING.CARRE;
import static model.RANKING.DEUX_PAIRES;
import static model.RANKING.FLUSH;
import static model.RANKING.FULL_HOUSE;
import static model.RANKING.HAUTEUR;
import static model.RANKING.PAIRE;
import static model.RANKING.STRAIGHT_FLUSH;
import static model.RANKING.SUITE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import business.HandValue;
import business.Jeu;
import model.Card;

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
		
		/*
		 * HIGH CARD
		 */
		//489TQ => QT984
		if (jeu.hasRankDiversity(5) && !jeu.isSameSuit() && !jeu.isSmallStraight() && !jeu.isSequential()) {
			return new HandValue(HAUTEUR, obtenirClassement(cinq, quatre, trois, deux, un));
		}
		
		/*
		 * ONE PAIR
		 */
		if (jeu.hasRankDiversity(4) && un==deux) {
			//22789 => 22987
				return new HandValue(PAIRE, obtenirClassement(un, deux, cinq, quatre, trois));
		}	
			//4JJQK => JJ4QK
		if (jeu.hasRankDiversity(4) && deux == trois) {
				return new HandValue(PAIRE, obtenirClassement(deux, trois, cinq, quatre, un));
		}
			//48QQK => QQK84
		if (jeu.hasRankDiversity(4) && trois == quatre) {
				return new HandValue(PAIRE, obtenirClassement(trois, quatre, cinq, deux, un));
		}
		if (jeu.hasRankDiversity(4) && quatre == cinq) {
			//48JQQ => QQJ74
			return new HandValue(PAIRE, obtenirClassement(quatre, cinq, trois, deux, un));
		}
		
		
        /*
         * DEUX PAIRES
         */
        if (jeu.hasRankDiversity(3) && jeu.hasRankMultipliciy(2) && un == deux && trois == quatre) {
        	//22338 => 33228
        	return new HandValue(DEUX_PAIRES, obtenirClassement(trois, quatre, un, deux, cinq));
        }
        
        if (jeu.hasRankDiversity(3) && jeu.hasRankMultipliciy(2) && un == deux && quatre == cinq) {
        	//228JJ => JJ228
        	return new HandValue(DEUX_PAIRES, obtenirClassement(quatre, cinq, un, deux, trois));
        }
        if (jeu.hasRankDiversity(3) && jeu.hasRankMultipliciy(2) && deux == trois && quatre == cinq) {
			//37799 => 99773
			return new HandValue(DEUX_PAIRES, obtenirClassement(quatre, cinq, deux, trois, un));
        }

		
		/*
		 * TRIPS
		 */
        if (jeu.hasRankDiversity(3) && jeu.hasRankMultipliciy(3) && un == deux && deux == trois) {
        	//3338T => 333T8
        	return new HandValue(BRELAN, obtenirClassement(un, deux, trois, cinq, quatre));
        }
        
        if (jeu.hasRankDiversity(3) && jeu.hasRankMultipliciy(3) && deux == trois && trois == quatre) {
        	//7999Q => 999Q7
        	return new HandValue(BRELAN, obtenirClassement(deux, trois, quatre, cinq, un));
        }
        
        if (jeu.hasRankDiversity(3) && jeu.hasRankMultipliciy(3) && quatre == trois && cinq == quatre) {
        	//45AAA => AAA54
        	return new HandValue(BRELAN, obtenirClassement(trois, quatre, cinq, deux, un));
        }
		
		
		/*
		 * SUITE
		 */
		if (jeu.hasRankDiversity(5) && !jeu.isSameSuit() && jeu.isSequential()) {
			return new HandValue(SUITE, obtenirClassement(cinq, quatre, trois, deux, un));
		}
		
		/*
		 * SMALL SUITE
		 */
		if (jeu.hasRankDiversity(5) && !jeu.isSameSuit() && jeu.isSmallStraight()) {
			return new HandValue(SUITE, obtenirClassement(quatre, trois, deux, un, cinq));
		}
		
		
		/*
		 * FLUSH
		 */
		if (jeu.isSameSuit() && !jeu.isSequential() && !jeu.isSmallStraight()) {
			//28QKA => AKQ82
			return new HandValue(FLUSH, obtenirClassement(cinq, quatre, trois, deux, un));
		}
		
		
		/*
		 * FULL HOUSE
		 */
		if (jeu.hasRankDiversity(2) && jeu.hasRankMultipliciy(3) && un == deux && deux != trois) {
			//par exemple 33JJJ => JJJ33
			return new HandValue(FULL_HOUSE, obtenirClassement(trois, quatre, cinq, un, deux));
		}
		
		if (jeu.hasRankDiversity(2) && jeu.hasRankMultipliciy(3) && un == deux && deux == trois) {
			//par exemple JJJQQ => JJJQQ
			return new HandValue(FULL_HOUSE, obtenirClassement(un, deux, trois, quatre, cinq));
		}
		
		
		/*
		 * QUADS
		 */
		if (jeu.hasRankDiversity(2) && jeu.hasRankMultipliciy(4) && un != deux) {
			//par exemple : 25555 => 55552
			return new HandValue(CARRE, obtenirClassement(deux, trois, quatre, cinq, un));
		}
		
		if (jeu.hasRankDiversity(2) && jeu.hasRankMultipliciy(4) && un == deux) {
			//par exemple 55559 => 55559
			return new HandValue(CARRE, obtenirClassement(deux, trois, quatre, cinq, un));
		}
		
		
		/*
		 * STRAIGHT FLUSH
		 */
		if (jeu.isSameSuit() && jeu.isSmallStraight()) {
			//23456
			return new HandValue(STRAIGHT_FLUSH, obtenirClassement(quatre, trois, deux, un, cinq));
		}
		if (jeu.isSameSuit() && jeu.isSequential()) {
			return new HandValue(STRAIGHT_FLUSH, obtenirClassement(cinq, quatre, trois, deux, un));
		}
		throw new IllegalStateException("error with cards " + jeu.get());
	}
	
	private static int[] obtenirClassement(int a, int b, int c, int d, int e) {
		return new int[] {a,b,c,d,e};
	}

}
