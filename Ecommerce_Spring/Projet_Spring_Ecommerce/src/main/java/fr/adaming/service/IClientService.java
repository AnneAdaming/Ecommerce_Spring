package fr.adaming.service;

import java.util.List;
import fr.adaming.model.Client;

public interface IClientService {
	public Client exists(Client c);
	public Client getClientById(long id);
	public List<Client> getAllClients();
	public Client addClient(Client c);
}
