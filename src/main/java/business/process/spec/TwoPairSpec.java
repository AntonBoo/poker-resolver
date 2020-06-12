package business.process.spec;

import java.util.List;

import business.Jeu;
import model.Card;
import model.RANKING;

public class TwoPairSpec implements HandSpec {

	@Override
	public boolean satisfyCondition(Jeu jeu) {
		return jeu.hasRankDiversity(3) && jeu.hasRankMultipliciy(2);
	}

	@Override
	public RANKING getRanking() {
		return RANKING.DEUX_PAIRES;
	}

	@Override
	public int[] getClassement(Jeu jeu) {
		List<Card> cartes = jeu.get();
		int un = cartes.get(0).getHauteur().getRank();
		int deux = cartes.get(1).getHauteur().getRank();
		int trois = cartes.get(2).getHauteur().getRank();
		int quatre = cartes.get(3).getHauteur().getRank();
		int cinq = cartes.get(4).getHauteur().getRank();
		if (un == deux && trois == quatre)
			return new int[] { trois, quatre, un, deux, cinq };
		if (un == deux && quatre == cinq)
			return new int[] { quatre, cinq, un, deux, trois };
		return new int[] { quatre, cinq, deux, trois, un };
	}

}
