package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Service("ligneCommandeService")
@Transactional
public class LigneCommandeServiceImpl implements ILigneCommandeService {
	@Autowired
	private ILigneCommandeDao ligneCommandeDao;

	public void setLigneCommandeDao(ILigneCommandeDao ligneCommandeDao) {
		this.ligneCommandeDao = ligneCommandeDao;
	}

	
	@Override
	public LigneCommande getLigneCommandeById(long id) {
		return ligneCommandeDao.getLigneCommandeById(id);
	}

	@Override
	public List<LigneCommande> getAllLigneCommandes() {
		return ligneCommandeDao.getAllLigneCommandes();
	}

	@Override
	public LigneCommande addLigneCommande(LigneCommande lc, Commande co, Produit pr) {
		if (lc==null || co==null || pr==null) {
			System.out.println("Erreur lors de l'ajout de la ligne de commande");
			return null;
		}
		lc.setCommande(co);
		lc.setProduit(pr);
		return ligneCommandeDao.addLigneCommande(lc);
	}
}
