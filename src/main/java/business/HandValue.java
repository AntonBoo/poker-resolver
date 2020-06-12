package business;

import java.util.Arrays;

import business.process.spec.HandSpec;
import model.RANKING;

public class HandValue {

	private final RANKING ranking;
	private int[] classement;

	public HandValue(RANKING ranking, int[] classement) {
		this.ranking = ranking;
		this.classement = classement;
	}

	public HandValue(HandSpec handSpec, final Jeu jeu) {
		this.ranking = handSpec.getRanking();
		this.classement = handSpec.getClassement(jeu);
	}

	public int[] getClassement() {
		return classement;
	}

	public void setClassement(int[] classement) {
		this.classement = classement;
	}

	public RANKING getRanking() {
		return ranking;
	}

	@Override
	public String toString() {
		return "HandValue [ranking=" + ranking + ", classement=" + Arrays.toString(classement) + "]";
	}

}
