package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

public interface IProduitService {
	public List<Produit> getAllProduit();
	public List<Produit> getAllProduitByCategorie(Categorie c);
	public Produit getProduitById(long id);
	public Produit addProduit(Produit p, Categorie c);
	public void deleteProduit(Produit p);
	public Produit modifyProduit(Produit p);
}
