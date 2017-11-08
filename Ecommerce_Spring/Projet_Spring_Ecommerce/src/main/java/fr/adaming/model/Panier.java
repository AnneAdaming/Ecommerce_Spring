package fr.adaming.model;

import java.util.List;

public class Panier {
	// Attributs
	private List<LigneCommande> liste;

	// Constructeur
	public Panier() {
		super();
	}

	// Getters / Setters
	public List<LigneCommande> getListe() {
		return liste;
	}
	public void setListe(List<LigneCommande> liste) {
		this.liste = liste;
	}

	@Override
	public String toString() {
		return "Panier []";
	}
}
