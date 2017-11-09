package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import fr.adaming.model.Categorie;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name="produitMB")
@RequestScoped
public class ProduitMB implements Serializable {
	private static final long serialVersionUID = 1L;

	// Attributs
	@ManagedProperty(value="#{produitService}")
	private IProduitService produitService;
	@ManagedProperty(value="#{categorieService}")
	private ICategorieService categorieService;
	private Produit produit;
	private String categorieIdString;
	private Panier panier;
	private LigneCommande ligne;
	private long idProduit;

	

	// Constructeur
	public ProduitMB() {
		super();
	}
	
	
	public void init(ComponentSystemEvent event) {
		List<Produit> listeProduits = produitService.getAllProduits();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeProduits", listeProduits);
		this.produit = new Produit();
		this.panier = (Panier) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("panier");
		if (panier == null) {
			this.panier = new Panier();
			this.panier.setListe(new ArrayList<LigneCommande>());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panier", panier);
		}
		ligne=new LigneCommande();
	}
	
	@PostConstruct
	public void init2(){
		this.produit = new Produit();
		this.panier = (Panier) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("panier");
		if (panier == null) {
			this.panier = new Panier();
			this.panier.setListe(new ArrayList<LigneCommande>());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panier", panier);
		}
		ligne=new LigneCommande();
	}
	
	// Getters / Setters
	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}
	public void setCategorieService(ICategorieService categorieService) {
		this.categorieService = categorieService;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public String getCategorieIdString() {
		return categorieIdString;
	}
	public void setCategorieIdString(String categorieIdString) {
		this.categorieIdString = categorieIdString;
	}

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


	// Methodes
	public String addProduit() {
		System.out.println("tentative d'ajout du produit "+produit);
		long idCategorie = Long.parseLong(this.categorieIdString);
		Categorie c = this.categorieService.getCategorieById(idCategorie);
		System.out.println("Ajout produit : " + produitService.addProduit(this.produit, c));
		List<Produit> listeProduits = produitService.getAllProduits();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeProduits", listeProduits);
		return "home.xhtml";
	}
	public String updateProduit() {
		long idCategorie = Long.parseLong(this.categorieIdString);
		Categorie c = this.categorieService.getCategorieById(idCategorie);
		produitService.updateProduit(this.produit, c);
		List<Produit> listeProduits = produitService.getAllProduits();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeProduits", listeProduits);
		return "home.xhtml";
	}
	public String deleteProduit() {
		produitService.deleteProduit(this.produit);
		List<Produit> listeProduits = produitService.getAllProduits();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeProduits", listeProduits);
		return "home.xhtml";
	}
	
	
	// TODO : actionlistener +test
	public String augmenter() {
		produit = produitService.getProduitById(idProduit);
		boolean existe = false;
		for (LigneCommande ligneP : panier.getListe()) {
			Produit produitCompare = ligneP.getProduit();
			if (produitCompare.getId() == produit.getId()) {
				if (produit.getQuantite() > ligneP.getQuantite()) {
					ligneP.setQuantite(ligneP.getQuantite() + 1);
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
			panier.getListe().add(ligne);
		}
		
		return "home";
	}

	public String diminuer() {
		produit = produitService.getProduitById(idProduit);
		boolean existe = false;
		for (LigneCommande ligneP : panier.getListe()) {
			Produit produitCompare = ligneP.getProduit();
			if (produitCompare.getId() == produit.getId()) {
				if (ligneP.getQuantite() == 1) {
					panier.getListe().remove(ligneP);
				} else if (ligneP.getQuantite() > 0) {
					ligneP.setQuantite(ligneP.getQuantite() - 1);
				}
				existe = true;
				break;
			}
		}
		if (existe == false) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Vous n'aviez pas sélectionné ce produit."));
		}
		
		return "home";
	}
}
