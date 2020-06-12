package business.process.spec;

import java.util.List;

import business.Jeu;
import model.Card;
import model.RANKING;

public class OnePairSpec implements HandSpec {

	@Override
	public boolean satisfyCondition(Jeu jeu) {
		return jeu.hasRankDiversity(4);
	}

	@Override
	public RANKING getRanking() {
		return RANKING.PAIRE;
	}

	@Override
	public int[] getClassement(Jeu jeu) {
		List<Card> cartes = jeu.get();
		int un = cartes.get(0).getHauteur().getRank();
		int deux = cartes.get(1).getHauteur().getRank();
		int trois = cartes.get(2).getHauteur().getRank();
		int quatre = cartes.get(3).getHauteur().getRank();
		int cinq = cartes.get(4).getHauteur().getRank();
		if (un == deux)
			return new int[] { un, deux, cinq, quatre, trois };
		if (deux == trois)
			return new int[] { deux, trois, cinq, quatre, un };
		if (trois == quatre)
			return new int[] { trois, quatre, cinq, deux, un };
		return new int[] { quatre, cinq, trois, deux, un };
	}

}
