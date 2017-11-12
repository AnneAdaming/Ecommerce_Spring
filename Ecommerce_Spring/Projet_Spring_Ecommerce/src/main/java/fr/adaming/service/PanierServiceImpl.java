package fr.adaming.service;

import fr.adaming.model.Client;
import fr.adaming.model.Panier;

public class PanierServiceImpl implements IPanierService {
	public void envoyerMail(Panier panier, Client client) {
		System.out.println("PanierServiceImpl -> envoyerMail()");
	}
	public void exporterPdf(Panier panier, Client client) {
		System.out.println("PanierServiceImpl -> exporterPDF()");
	}
}
