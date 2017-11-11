package fr.adaming.managedBeans;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;

@ManagedBean(name="panierMB")
@RequestScoped
public class PanierMB {
	private Panier panier;
	private LigneCommande ligne;
	private Client client;
	private long total;	
	private long idProduit;
	
	@ManagedProperty(value="#{produitService}")
	private IProduitService produitService2;
	@ManagedProperty(value="#{clientService}")
	private IClientService clientService;
	@ManagedProperty(value="#{commandeService}")
	private ICommandeService commandeService;
	@ManagedProperty(value="#{produitService}")
	private IProduitService produitService;
	@ManagedProperty(value="#{ligneCommandeService}")
	private ILigneCommandeService ligneCommandeService;
	
	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public LigneCommande getLigne() {
		return ligne;
	}

	public void setLigne(LigneCommande ligne) {
		this.ligne = ligne;
	}

	public long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(long idProduit) {
		this.idProduit = idProduit;
	}

	public void setProduitService2(IProduitService produitService2) {
		this.produitService2 = produitService2;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
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

	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@PostConstruct
	public void init(){
		this.panier = (Panier) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("panier");
		if (panier == null) {
			this.panier = new Panier();
			this.panier.setListe(new ArrayList<LigneCommande>());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panier", panier);
		}
		ligne=new LigneCommande();
		client=new Client();
	}
	
	public String augmenter() {
		System.out.println("Total : "+total);
		Produit produit = produitService2.getProduitById(idProduit);
		boolean existe = false;
		for (LigneCommande ligneP : panier.getListe()) {
			Produit produitCompare = ligneP.getProduit();
			if (produitCompare.getId() == produit.getId()) {
				if (produit.getQuantite() > ligneP.getQuantite()) {
					ligneP.setQuantite(ligneP.getQuantite() + 1);
					ligneP.setTotal(ligneP.getTotal()+produit.getPrix());
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Vous ne pouvez plus rajouter de ce produit."));
				}
				existe = true;
				break;
			}
		}
		if (existe == false) {
			ligne.setProduit(produit);
			ligne.setQuantite(1);
			ligne.setPrix(produit.getPrix());
			ligne.setTotal(produit.getPrix());
			panier.getListe().add(ligne);
		}
		total = 0;
		for (LigneCommande ligne : panier.getListe()) {
			total += ligne.getTotal();
			System.out.println("total de la ligne : "+ligne.getTotal());
		}
		System.out.println("Total après calcul"+total);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("total", total);
		return "home";
		
	}

	public String diminuer() {
		Produit produit = produitService2.getProduitById(idProduit);
		boolean existe = false;
		for (LigneCommande ligneP : panier.getListe()) {
			Produit produitCompare = ligneP.getProduit();
			if (produitCompare.getId() == produit.getId()) {
				if (ligneP.getQuantite() == 1) {
					panier.getListe().remove(ligneP);
				} else if (ligneP.getQuantite() > 0) {
					ligneP.setQuantite(ligneP.getQuantite() - 1);
					ligneP.setTotal(ligneP.getTotal()-produit.getPrix());
				}
				existe = true;
				break;
			}
		}
		if (existe == false) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Vous n'aviez pas sélectionné ce produit."));
		}
		total = 0;
		for (LigneCommande ligne : panier.getListe()) {
			total += ligne.getTotal();
		}
		System.out.println("Total après calcul"+total);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("total", total);
		return "home";
	}
	
	public String valider() {
		client = clientService.addClient(client);
		Date date = new Date();
		Commande commande = new Commande(date);
		commande.setListeLigneCommande(panier.getListe());
		commande = commandeService.addCommande(commande, client);
		for (LigneCommande l:panier.getListe()) {
			Produit produit = l.getProduit();
			int qte = produit.getQuantite() - l.getQuantite();
			produit.setQuantite(qte);
			produit = produitService.updateProduit(produit, produit.getCategorie());
			ligneCommandeService.addLigneCommande(l, commande, produit);
		}
		this.ligne = new LigneCommande();
		this.client = new Client();
		panier.setListe(null);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Un mail récapitulatif de la commande vous a été envoyé."));
		return "home";
	}
	
	public void validerNonVide(FacesContext contexte, UIComponent composant, Object valeur)
			throws ValidatorException {
		String saisie=(String) valeur;
		if (saisie.length()<1) {
			throw new ValidatorException(new FacesMessage("Ce champ doit être rempli."));
		}
	
	}


}
