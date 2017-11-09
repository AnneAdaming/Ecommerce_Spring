package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

public interface ICommandeDao {
	public Commande getCommandeById(long id);
	public List<Commande> getAllCommandes();
	Commande addCommande(Commande co);
}
