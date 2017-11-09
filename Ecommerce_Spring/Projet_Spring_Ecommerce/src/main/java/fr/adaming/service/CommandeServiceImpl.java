package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Service("commandeService")
@Transactional
public class CommandeServiceImpl implements ICommandeService {
	@Autowired
	private ICommandeDao commandeDao;

	public void setCommandeDao(ICommandeDao commandeDao) {
		this.commandeDao = commandeDao;
	}

	
	@Override
	public Commande getCommandeById(long id) {
		return commandeDao.getCommandeById(id);
	}

	@Override
	public List<Commande> getAllCommandes() {
		return commandeDao.getAllCommandes();
	}

	@Override
	public Commande addCommande(Commande co, Client cl) {
		if (co==null || cl==null) {
			System.out.println("Erreur lors de l'ajout de la commande");
			return null;
		}
		co.setClient(cl);
		return commandeDao.addCommande(co);
	}
}
