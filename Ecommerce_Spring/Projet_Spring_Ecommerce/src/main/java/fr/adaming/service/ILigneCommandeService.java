package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

public interface ILigneCommandeService {
	public LigneCommande getLigneCommandeById(long id);
	public List<LigneCommande> getAllLigneCommandes();
	public LigneCommande addLigneCommande(LigneCommande lc, Commande co, Produit pr);
}
