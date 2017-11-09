package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name="produitMB")
@RequestScoped
public class ProduitMB implements Serializable {
	private static final long serialVersionUID = 1L;

	// Attributs
	@ManagedProperty(value="#{produitService}")
	private IProduitService produitService;
	private List<Produit> listeProduits;
	private Produit produit;
	private Categorie categorie;

	// Constructeur
	public ProduitMB() {
		super();
	}
	@PostConstruct
	private void init() {
		this.listeProduits = produitService.getAllProduits();
		this.produit = new Produit();
	}
	
	// Getters / Setters
	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}
	public List<Produit> getListeProduits() {
		return listeProduits;
	}
	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	// Methodes
	public String addProduit() {
		produitService.addProduit(this.produit, this.categorie);
		return "home.xhtml";
	}
	public String updateProduit() {
		produitService.updateProduit(this.produit, this.categorie);
		return "home.xhtml";
	}
	public String deleteProduit() {
		produitService.deleteProduit(this.produit);
		return "home.xhtml";
	}
}
