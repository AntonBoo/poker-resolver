package business.process.spec;

import java.util.List;

import business.Jeu;
import model.Card;
import model.RANKING;

public class QuadsSpec implements HandSpec {

	@Override
	public boolean satisfyCondition(Jeu jeu) {
		return jeu.hasRankDiversity(2) && jeu.hasRankMultipliciy(4);
	}

	@Override
	public RANKING getRanking() {
		return RANKING.CARRE;
	}

	@Override
	public int[] getClassement(Jeu jeu) {
		List<Card> cartes = jeu.get();
		int un = cartes.get(0).getHauteur().getRank();
		int deux = cartes.get(1).getHauteur().getRank();
		int trois = cartes.get(2).getHauteur().getRank();
		int quatre = cartes.get(3).getHauteur().getRank();
		int cinq = cartes.get(4).getHauteur().getRank();
		if (un != deux) {
			// par exemple : 25555 => 55552
			return new int[] { deux, trois, quatre, cinq, un };
		}
		// par exemple 55559 => 55559
		return new int[] { un, deux, trois, quatre, cinq };
	}

}
