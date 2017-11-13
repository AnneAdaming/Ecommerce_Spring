package fr.adaming.managedBeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.IPanierService;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;

@ManagedBean(name="panierMB")
@RequestScoped
public class PanierMB {
	private Client client;
	
	@ManagedProperty(value="#{clientService}")
	private IClientService clientService;
	@ManagedProperty(value="#{commandeService}")
	private ICommandeService commandeService;
	@ManagedProperty(value="#{produitService}")
	private IProduitService produitService;
	@ManagedProperty(value="#{ligneCommandeService}")
	private ILigneCommandeService ligneCommandeService;
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}
	public void setCommandeService(ICommandeService commandeService) {
		this.commandeService = commandeService;
	}
	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}
	public void setLigneCommandeService(ILigneCommandeService ligneCommandeService) {
		this.ligneCommandeService = ligneCommandeService;
	}
	
	@PostConstruct
	public void init(){
		Panier panier = (Panier) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("panier");
		if (panier == null) {
			panier = new Panier();
			panier.setListeLignesCommande(new ArrayList<LigneCommande>());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panier", panier);
		}
		this.client = new Client();
	}
	
	public void ajouter(Produit produit) {
		Panier panier = (Panier) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("panier");
		int occurs = 0;
		for (LigneCommande ligneP : panier.getListeLignesCommande()) {
			if (produit.getId() == ligneP.getProduit().getId()) {
				if (ligneP.getQuantite() == produit.getQuantite()) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Les stocks de ce produit sont épuisés"));
					return;
				}
				ligneP.setQuantite(ligneP.getQuantite()+1);
				ligneP.setTotal(ligneP.getTotal()+produit.getPrix());
				occurs = ligneP.getQuantite();
				break;
			}
		}
		if (occurs == 0) {
			LigneCommande ligne = new LigneCommande(1, produit.getPrix());
			ligne.setProduit(produit);
			ligne.setTotal(produit.getPrix());
			panier.getListeLignesCommande().add(ligne);
			occurs = 1;
		}
		double total = 0;
		for (LigneCommande ligne : panier.getListeLignesCommande()) {
			total += ligne.getTotal();
		}
		panier.setTotal(total);
		System.out.println("total panier : " + panier.getTotal());
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panier", panier);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(occurs + " " + produit.getDesignation() + " dans le panier"));
	}

	public void retirer(Produit produit) {
		Panier panier = (Panier) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("panier");
		int occurs = -1;
		for (LigneCommande ligneP : panier.getListeLignesCommande()) {
			if (produit.getId() == ligneP.getProduit().getId()) {
				if (ligneP.getQuantite() > 0) {
					if (ligneP.getQuantite() == 1) {
						panier.getListeLignesCommande().remove(ligneP);
						occurs = 0;
					} else {
						ligneP.setQuantite(ligneP.getQuantite()-1);
						ligneP.setTotal(ligneP.getTotal()-produit.getPrix());
						occurs = ligneP.getQuantite();
					}
					break;
				}
			}
		}
		if (occurs == -1) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ce produit n'est pas dans votre panier."));
			return;
		}
		double total = 0;
		for (LigneCommande ligne : panier.getListeLignesCommande()) {
			total += ligne.getTotal();
		}
		panier.setTotal(total);
		System.out.println("total panier : " + panier.getTotal());
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panier", panier);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(occurs + " " + produit.getDesignation() + " dans le panier"));
	}
	
	public String valider() {
		Panier panier = (Panier) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("panier");
		if (panier.getListeLignesCommande().size() == 0) {
			return "panier.xhtml";
		}
		Client clientDB = clientService.exists(this.client);
		if (clientDB == null) {
			this.client = clientService.addClient(clientDB);
		} else {
			this.client = clientDB;
		}
		Commande commande = new Commande(new Date());
		commande.setListeLigneCommande(panier.getListeLignesCommande());
		commande = commandeService.addCommande(commande, client);
		for (LigneCommande l : panier.getListeLignesCommande()) {
			Produit produit = l.getProduit();
			produit.setQuantite(produit.getQuantite() - l.getQuantite());
			ligneCommandeService.addLigneCommande(l, commande, produit);
		}
		exporterPdf(panier, client);
		envoyerMail(panier, client);
		this.client = new Client();
		panier.setTotal(0.0);
		panier.setListeLignesCommande(null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panier", panier);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Un mail récapitulatif de la commande vous a été envoyé."));
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "home";
	}
	
	public void exporterPdf(Panier panier, Client client) {
		@SuppressWarnings("resource")
//		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-context.xml");
		ApplicationContext context = new FileSystemXmlApplicationContext("C:/Users/inti/git/Ecommerce_Spring/Ecommerce_Spring/Projet_Spring_Ecommerce/src/main/webapp/WEB-INF/application-context.xml");
		IPanierService panierService = (IPanierService) context.getBean("panierServiceBean");
		panierService.exporterPdf(panier, client);
	}
	public void envoyerMail(Panier panier, Client client) {
		@SuppressWarnings("resource")
//		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-context.xml");
		ApplicationContext context = new FileSystemXmlApplicationContext("C:/Users/inti/git/Ecommerce_Spring/Ecommerce_Spring/Projet_Spring_Ecommerce/src/main/webapp/WEB-INF/application-context.xml");
		IPanierService panierService = (IPanierService) context.getBean("panierServiceBean");
		panierService.envoyerMail(panier, client);
	}
}
