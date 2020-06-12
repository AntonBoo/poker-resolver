package business.process.spec;

import java.util.List;

import business.Jeu;
import model.Card;
import model.RANKING;

public class FullHouseSpec implements HandSpec {

	@Override
	public boolean satisfyCondition(Jeu jeu) {
		return jeu.hasRankDiversity(2) && jeu.hasRankMultipliciy(3);
	}

	@Override
	public RANKING getRanking() {
		return RANKING.FULL_HOUSE;
	}

	@Override
	public int[] getClassement(Jeu jeu) {
		List<Card> cartes = jeu.get();
		int un = cartes.get(0).getHauteur().getRank();
		int deux = cartes.get(1).getHauteur().getRank();
		int trois = cartes.get(2).getHauteur().getRank();
		int quatre = cartes.get(3).getHauteur().getRank();
		int cinq = cartes.get(4).getHauteur().getRank();
		if (un == deux && deux != trois) {
			// par exemple 33JJJ => JJJ33
			return new int[] { trois, quatre, cinq, un, deux };
		}
		// par exemple JJJQQ => JJJQQ
		return new int[] { un, deux, trois, quatre, cinq };
	}

}
