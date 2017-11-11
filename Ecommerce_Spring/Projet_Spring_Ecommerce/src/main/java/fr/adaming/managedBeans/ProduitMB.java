package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.imageio.ImageIO;

import org.primefaces.model.UploadedFile;

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
	private UploadedFile imageFichier;
	

	// Constructeur
	public ProduitMB() {
		super();
	}
	@PostConstruct
	public void init(){
		this.produit = new Produit();
	}
	public void initListener(ComponentSystemEvent event) {
		List<Produit> listeProduits = produitService.getAllProduits();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeProduits", listeProduits);
		this.init();
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
	public UploadedFile getImageFichier() {
		return imageFichier;
	}
	public void setImageFichier(UploadedFile imageFichier) {
		this.imageFichier = imageFichier;
	}
	// Methodes
	public String addProduit() {
		if (imageFichier==null) {
			System.out.println("Scrogneugneu");
		} else {
			System.out.println("yay ?");
			System.out.println(imageFichier);
		}
		System.out.println(produit);
		produit.setImage(imageFichier.getContents());
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
	
	
	
}
