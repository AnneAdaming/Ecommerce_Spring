package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name="produitMB")
@RequestScoped
public class ProduitMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{produitService}")
	private IProduitService produitService;
	private Produit produit;
	
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}
	
}
