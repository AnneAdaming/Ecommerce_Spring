package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import fr.adaming.model.Categorie;
import fr.adaming.service.ICategorieService;

@ManagedBean(name="categorieMB")
@RequestScoped
public class CategorieMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{categorieService}")
	private ICategorieService categorieService;
	private Categorie categorie;
	
	public void setCategorieService(ICategorieService categorieService) {
		this.categorieService = categorieService;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	
	
}
