package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import fr.adaming.model.Categorie;
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
	

	// Constructeur
	public ProduitMB() {
		super();
	}
	
	
	@PostConstruct
	public void init(ComponentSystemEvent event) {
		
		List<Produit> listeProduits = produitService.getAllProduits();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeProduits", listeProduits);
		this.produit = new Produit();
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

	// Methodes
	public String addProduit() {
		long idCategorie = Long.parseLong(this.categorieIdString);
		Categorie c = this.categorieService.getCategorieById(idCategorie);
		produitService.addProduit(this.produit, c);
		List<Produit> listeProduits = produitService.getAllProduits();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeProduits", listeProduits);
		return "home.xhtml";
		
	}
	public String updateProduit() {
		long idCategorie = Long.parseLong(this.categorieIdString);
		Categorie c = this.categorieService.getCategorieById(idCategorie);
		System.out.println("update : categorie=" + c);
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
}
