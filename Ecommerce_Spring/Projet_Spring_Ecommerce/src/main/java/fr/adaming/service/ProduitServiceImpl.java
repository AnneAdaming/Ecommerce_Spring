package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IProduitDao;

@Service("produitService")
@Transactional
public class ProduitServiceImpl implements IProduitService {
	@Autowired
	private IProduitDao produitDao;

	public void setProduitDao(IProduitDao produitDao) {
		this.produitDao = produitDao;
	}
}
