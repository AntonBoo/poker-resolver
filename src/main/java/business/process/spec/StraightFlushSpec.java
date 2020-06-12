package business.process.spec;

import java.util.List;

import business.Jeu;
import model.Card;
import model.RANKING;

public class StraightFlushSpec implements HandSpec {

	@Override
	public boolean satisfyCondition(Jeu jeu) {
		return jeu.isSameSuit() && (jeu.isSmallStraight() || jeu.isSequential());
	}

	@Override
	public RANKING getRanking() {
		return RANKING.STRAIGHT_FLUSH;
	}

	@Override
	public int[] getClassement(Jeu jeu) {
		List<Card> cartes = jeu.get();
		int un = cartes.get(0).getHauteur().getRank();
		int deux = cartes.get(1).getHauteur().getRank();
		int trois = cartes.get(2).getHauteur().getRank();
		int quatre = cartes.get(3).getHauteur().getRank();
		int cinq = cartes.get(4).getHauteur().getRank();
		if (jeu.isSequential())
			return new int[] { cinq, quatre, trois, deux, un };
		return new int[] { quatre, trois, deux, un, cinq };
	}

}
