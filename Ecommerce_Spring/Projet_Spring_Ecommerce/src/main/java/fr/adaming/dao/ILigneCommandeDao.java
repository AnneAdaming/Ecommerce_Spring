package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.LigneCommande;

public interface ILigneCommandeDao {
	public LigneCommande getLigneCommandeById(long id);
	public List<LigneCommande> getAllLigneCommandes();
	public LigneCommande addLigneCommande(LigneCommande lc);
}
