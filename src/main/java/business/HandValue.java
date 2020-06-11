package business;

import java.util.Arrays;

import model.RANKING;

public class HandValue {
	
	private final RANKING ranking;
	private int[] classement;
	
	public HandValue(RANKING ranking, int[] classement) {
		this.ranking = ranking;
		this.classement = classement;
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
