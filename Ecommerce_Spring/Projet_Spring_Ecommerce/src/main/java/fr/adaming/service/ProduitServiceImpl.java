package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Service("produitService")
@Transactional
public class ProduitServiceImpl implements IProduitService {
	@Autowired
	private IProduitDao produitDao;

	public void setProduitDao(IProduitDao produitDao) {
		this.produitDao = produitDao;
	}
	

	@Override
	public Produit getProduitById(long id){
		return produitDao.getProduitById(id);
	}

	@Override
	public List<Produit> getAllProduits(){
		return produitDao.getAllProduits();
	}

	@Override
	public List<Produit> getAllProduitsByCategorie(Categorie c) {
		if (c==null) {
			System.out.println("Erreur lors de la recuperation de la liste des produits par categorie");
			return null;
		}
		return produitDao.getAllProduitsByCategorie(c);
	}

	@Override
	public Produit addProduit(Produit p, Categorie c) {
		if (p==null || c==null) {
			System.out.println("Erreur lors de l'ajout du produit");
			return null;
		}
		p.setCategorie(c);
		return produitDao.addProduit(p);
	}

	@Override
	public Produit updateProduit(Produit p, Categorie c) {
		if (p==null) {
			System.out.println("Erreur lors de la modification du produit");
			return null;
		}
		p.setCategorie(c);
		return produitDao.updateProduit(p);
	}

	@Override
	public void deleteProduit(Produit p) {
		if (p==null) {
			System.out.println("Erreur lors de la suppression du produit");
			return;
		}
		produitDao.deleteProduit(p);
	}
}
