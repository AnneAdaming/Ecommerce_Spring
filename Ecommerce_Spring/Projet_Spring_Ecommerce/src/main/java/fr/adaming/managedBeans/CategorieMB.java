package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import fr.adaming.model.Categorie;
import fr.adaming.service.ICategorieService;

@ManagedBean(name="categorieMB")
@RequestScoped
public class CategorieMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Attributs
	@ManagedProperty(value="#{categorieService}")
	private ICategorieService categorieService;
	private List<Categorie> listeCategories;
	private Categorie categorie;

	// Constructeur
	public CategorieMB() {
		super();
	}
	@PostConstruct
	private void init() {
		this.listeCategories = categorieService.getAllCategories();
		this.categorie = new Categorie();
	}
	
	// Getters / Setters
	public void setCategorieService(ICategorieService categorieService) {
		this.categorieService = categorieService;
	}
	public List<Categorie> getListeCategories() {
		return listeCategories;
	}
	public void setListeCategories(List<Categorie> listeCategories) {
		this.listeCategories = listeCategories;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	// Methodes
	public String addCategorie() {
		categorieService.addCategorie(this.categorie);
		return "home.xhtml";
	}
	public String updateCategorie() {
		categorieService.updateCategorie(this.categorie);
		return "home.xhtml";
	}
	public String deleteCategorie() {
		categorieService.deleteCategorie(this.categorie);
		return "home.xhtml";
	}
}
