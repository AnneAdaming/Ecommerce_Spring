package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;

@Service("clientService")
@Transactional
public class ClientServiceImpl implements IClientService {
	@Autowired
	private IClientDao clientDao;

	public void setClientDao(IClientDao clientDao) {
		this.clientDao = clientDao;
	}


	@Override
	public Client exists(Client c) {
		if (c==null) {
			System.out.println("Erreur lors de la vérification de validité du client");
			return null;
		}
		return clientDao.exists(c);
	}
	
	@Override
	public Client getClientById(long id) {
		return clientDao.getClientById(id);
	}

	@Override
	public List<Client> getAllClients() {
		return clientDao.getAllClients();
	}

	@Override
	public Client addClient(Client c) {
		if (c==null) {
			System.out.println("Erreur lors de l'ajout du client");
			return null;
		}
		return clientDao.addClient(c);
	}
}
