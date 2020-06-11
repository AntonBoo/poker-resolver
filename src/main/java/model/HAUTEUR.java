package model;

public enum HAUTEUR {
	
	AS(14),
	ROI(13),
	DAME(12),
	VALET(11),
	DIX(10),
	NEUF(9),
	HUIT(8),
	SEPT(7),
	SIX(6),
	CINQ(5),
	QUATRE(4),
	TROIS(3),
	DEUX(2);
	
	private int rank;
	
	private HAUTEUR(int rank) {
		this.rank = rank;
	}
	
	public int getRank() {
		return rank;
	}

}
