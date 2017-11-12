package fr.adaming.service;

import fr.adaming.model.Client;
import fr.adaming.model.Panier;

public interface IPanierService {
	public void envoyerMail(Panier panier, Client client);
	public void exporterPdf(Panier panier, Client client);
}
