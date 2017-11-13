package fr.adaming.dao;

import java.util.List;
import fr.adaming.model.Client;

public interface IClientDao {
	public Client exists(Client c);
	public Client getClientById(long id);
	public List<Client> getAllClients();
	public Client addClient(Client c);
}
