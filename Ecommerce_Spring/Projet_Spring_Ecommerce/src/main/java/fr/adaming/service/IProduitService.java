package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

public interface IProduitService {
	public Produit getProduitById(long id);
	public List<Produit> getAllProduits();
	public List<Produit> getAllProduitsByCategorie(Categorie c);
	public Produit addProduit(Produit p, Categorie c);
	public Produit updateProduit(Produit p, Categorie c);
	public void deleteProduit(Produit p);
}
