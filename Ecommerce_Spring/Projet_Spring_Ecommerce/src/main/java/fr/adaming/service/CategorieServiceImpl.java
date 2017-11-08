package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICategorieDao;

@Service("categorieService")
@Transactional
public class CategorieServiceImpl implements ICategorieService {
	@Autowired
	private ICategorieDao categorieDao;

	public void setCategorieDao(ICategorieDao categorieDao) {
		this.categorieDao = categorieDao;
	}
}
