package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

public class Panier implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Attributs
	private List<LigneCommande> listeLignesCommande;
	private double total;

	// Constructeur
	public Panier() {
		super();
	}

	// Getters / Setters
	public List<LigneCommande> getListeLignesCommande() {
		return listeLignesCommande;
	}
	public void setListeLignesCommande(List<LigneCommande> listeLignesCommande) {
		this.listeLignesCommande = listeLignesCommande;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Panier [total=" + total + "]";
	}
}
