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
	
	public List<Produit> getAllProduit(){
		return produitDao.getAllProduit();
	}
	
	public List<Produit> getAllProduitByCategorie(Categorie c){
		return produitDao.getAllProduitByCategorie(c);
	}
	
	public Produit getProduitById(long id){
		return null;
	}
	
	public Produit addProduit(Produit p, Categorie c){
		return null;
	}
	
	public void deleteProduit(Produit p){
		
	}
	
	public Produit modifyProduit(Produit p){
		return null;
	}
	
}
