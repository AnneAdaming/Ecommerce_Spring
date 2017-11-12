package fr.adaming.managedBeans;

import java.io.IOException;
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
import fr.adaming.service.ICategorieService;

@ManagedBean(name="categorieMB")
@RequestScoped
public class CategorieMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Attributs
	@ManagedProperty(value="#{categorieService}")
	private ICategorieService categorieService;
	private Categorie categorie;

	// Constructeur
	public CategorieMB() {
		super();
	}
	@PostConstruct
	public void init() {
		this.categorie = new Categorie();
	}
	public void initListener(ComponentSystemEvent event) {
		List<Categorie> listeCategories = categorieService.getAllCategories();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCategories", listeCategories);
		this.init();
	}
	
	// Getters / Setters
	public void setCategorieService(ICategorieService categorieService) {
		this.categorieService = categorieService;
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
		List<Categorie> listeCategories = categorieService.getAllCategories();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCategories", listeCategories);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "home.xhtml";
	}
	public String updateCategorie() {
		categorieService.updateCategorie(this.categorie);
		List<Categorie> listeCategories = categorieService.getAllCategories();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCategories", listeCategories);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "home.xhtml";
	}
	public String deleteCategorie() {
		categorieService.deleteCategorie(this.categorie);
		List<Categorie> listeCategories = categorieService.getAllCategories();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCategories", listeCategories);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "home.xhtml";
	}
}
