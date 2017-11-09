package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

public interface IProduitDao {
	public Produit getProduitById(long id);
	public List<Produit> getAllProduits();
	public List<Produit> getAllProduitsByCategorie(Categorie c);
	public Produit addProduit(Produit p);
	public Produit updateProduit(Produit p);
	public void deleteProduit(Produit p);
}
