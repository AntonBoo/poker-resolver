package business.process.spec;

import java.util.List;

import business.Jeu;
import model.Card;
import model.RANKING;

public class TripsSpec implements HandSpec {

	@Override
	public boolean satisfyCondition(Jeu jeu) {
		return jeu.hasRankDiversity(3) && jeu.hasRankMultipliciy(3);
	}

	@Override
	public RANKING getRanking() {
		return RANKING.BRELAN;
	}

	@Override
	public int[] getClassement(Jeu jeu) {
		List<Card> cartes = jeu.get();
		int un = cartes.get(0).getHauteur().getRank();
		int deux = cartes.get(1).getHauteur().getRank();
		int trois = cartes.get(2).getHauteur().getRank();
		int quatre = cartes.get(3).getHauteur().getRank();
		int cinq = cartes.get(4).getHauteur().getRank();
		//3338T => 333T8
		if (un == deux && deux == trois)
			return new int[] { un, deux, trois, cinq, quatre };
		//7999Q => 999Q7
		if (deux == trois && trois==quatre) return new int[] {deux, trois, quatre, cinq, un};
		//45AAA => AAA54
		return new int[] {trois, quatre, cinq, deux, un};
	}

}
