package business.process;

import java.util.Arrays;

import business.HandValue;
import business.Jeu;

public class Comparator {
	
	/*
	 * return 1 if jeu1 > jeu2
	 * return 0 if jeu1 = jeu2
	 * return -1 if jeu1 < jeu2
	 */
	public static int fight(Jeu jeu1, Jeu jeu2) {
		HandValue hand1 = HandValueCalculator.process(jeu1);
		HandValue hand2 = HandValueCalculator.process(jeu2);
		return compare(hand1, hand2);
	}
	
	
	public static int compare(HandValue hand1, HandValue hand2) {
		int rang1 = hand1.getRanking().ordinal();
		int rang2 = hand2.getRanking().ordinal();
		if (rang1 < rang2) {
			return 1;
		}
		else if (rang1 > rang2) {
			return -1;
		}
		else {
			int[] classement1 = hand1.getClassement();
			int[] classement2 = hand2.getClassement();
			
			Integer[] diff = calculateDiff(classement1, classement2);
			if (Arrays.asList(diff).stream().allMatch(el -> el == 0)) {
				return 0;
			}
			
			if (Arrays.asList(diff).stream().filter(el -> el != 0).findFirst().get() > 0) {
				return 1;
			}
			return -1;
		}
	}


	private static Integer[] calculateDiff(int[] classement1, int[] classement2) {
		return new Integer[] {
				classement1[0] - classement2[0],
				classement1[1] - classement2[1],
				classement1[2] - classement2[2],
				classement1[3] - classement2[3],
				classement1[4] - classement2[4]};
	}

}
