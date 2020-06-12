package business.process.spec;

import business.Jeu;
import model.RANKING;

public interface HandSpec {
	
	boolean satisfyCondition(Jeu jeu);
	
	RANKING getRanking();
	
	int[] getClassement(Jeu jeu);

}
