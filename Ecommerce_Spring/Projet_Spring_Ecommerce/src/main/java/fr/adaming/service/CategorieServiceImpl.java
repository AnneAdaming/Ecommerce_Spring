package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;

@Service("categorieService")
@Transactional
public class CategorieServiceImpl implements ICategorieService {
	@Autowired
	private ICategorieDao categorieDao;

	public void setCategorieDao(ICategorieDao categorieDao) {
		this.categorieDao = categorieDao;
	}

	
	@Override
	public List<Categorie> getAllCategories() {
		return categorieDao.getAllCategories();
	}

	@Override
	public Categorie getCategorieById(long id) {
		return categorieDao.getCategorieById(id);
	}
	
	@Override
	public Categorie addCategorie(Categorie c) {
		if (c==null) {
			System.out.println("Erreur lors de l'ajout de la categorie");
			return null;
		}
		return categorieDao.addCategorie(c);
	}

	@Override
	public Categorie updateCategorie(Categorie c) {
		if (c==null) {
			System.out.println("Erreur lors de la modification de la categorie");
			return null;
		}
		return categorieDao.updateCategorie(c);
	}

	@Override
	public void deleteCategorie(Categorie c) {
		System.out.println("Service : "+c);
		if (c==null) {
			System.out.println("Erreur lors de la suppression de la categorie");
			return;
		}
		categorieDao.deleteCategorie(c);
	}
}
