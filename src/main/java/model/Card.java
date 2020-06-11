package model;

public class Card implements Comparable<Card> {
	
	private HAUTEUR hauteur;
	private COULEUR couleur;
	
	private Card(HAUTEUR hauteur, COULEUR couleur) {
		this.hauteur = hauteur;
		this.couleur = couleur;
	}
	
	public static Card create(HAUTEUR hauteur, COULEUR couleur) {
		return new Card(hauteur, couleur);
	}

	public HAUTEUR getHauteur() {
		return hauteur;
	}

	public COULEUR getCouleur() {
		return couleur;
	}

	@Override
	public String toString() {
		return "Card [hauteur=" + hauteur + ", couleur=" + couleur + ", ordinal=" +hauteur.ordinal() + "]";
	}

	@Override
	public int compareTo(Card o) {
		return new Integer(hauteur.getRank()).compareTo(o.getHauteur().getRank());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((couleur == null) ? 0 : couleur.hashCode());
		result = prime * result + ((hauteur == null) ? 0 : hauteur.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (couleur != other.couleur)
			return false;
		if (hauteur != other.hauteur)
			return false;
		return true;
	}
}
