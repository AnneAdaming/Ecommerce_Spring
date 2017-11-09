package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

public interface ICommandeService {
	public Commande getCommandeById(long id);
	public List<Commande> getAllCommandes();
	Commande addCommande(Commande co, Client cl);
}
