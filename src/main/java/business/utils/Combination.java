package business.utils;

import static model.RANKING.HAUTEUR;

import business.HandValue;
import business.Jeu;
import business.process.Comparator;
import business.process.HandValueCalculator;
import model.Card;

public class Combination {
	
	private HandValue bestHand = new HandValue(HAUTEUR, new int[] {0,0,0,0,0});

	// The main function that prints all combinations of size r
	// in arr[] of size n. This function mainly uses combinationUtil()
	public HandValue printCombination(Card[] cards, int n, int r) {
		// A temporary array to store all combination one by one
		Card[] data = new Card[r];

		// Print all combination using temprary array 'data[]'
		combinationUtil(cards, data, 0, n - 1, 0, r);
		return bestHand;
	}

	public HandValue getBestHand() {
		return bestHand;
	}

	private  void combinationUtil(Card arr[], Card data[], int start, int end, int index, int r) {
// Current combination is ready to be printed, print it 
		if (index == r) {
			final Card[] uneCombinaison = new Card[5];
			for (int j = 0; j < r; j++) {
				uneCombinaison[j] = data[j];
			}
			Jeu jeu = new Jeu(uneCombinaison[0], uneCombinaison[1], uneCombinaison[2], uneCombinaison[3], uneCombinaison[4]);
			HandValue currentHandValue = HandValueCalculator.process(jeu);
			if (Comparator.compare(currentHandValue, bestHand) > 0) {
				bestHand = currentHandValue;
			}
			return;
		}

		// replace index with all possible elements. The condition 
		// "end-i+1 >= r-index" makes sure that including one element 
		// at index will make a combination with remaining elements 
		// at remaining positions 
		for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
			data[index] = arr[i];
			combinationUtil(arr, data, i + 1, end, index + 1, r);
		}
		
	}
}
