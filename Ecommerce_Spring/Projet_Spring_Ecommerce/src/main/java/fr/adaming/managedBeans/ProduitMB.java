package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

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
	
	
	public void test() {
		Categorie c=new Categorie();
		c.setId(1);
		List<Produit> liste=produitService.getAllProduitByCategorie(c);
		for(Produit p:liste) {
			System.out.println(p);
		}
	}
	
}
