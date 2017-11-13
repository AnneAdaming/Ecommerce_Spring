package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Client;

@Repository
public class ClientDaoImpl implements IClientDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

	@Override
	public Client exists(Client c) {
		Session session = sessionFactory.getCurrentSession();
		String req = "FROM Client c WHERE c.email=:pEmail";
		Query query = session.createQuery(req);
		query.setParameter("pEmail", c.getEmail());
		Client cOut = (Client) query.uniqueResult();
		return cOut;
	}
	
	@Override
	public Client getClientById(long id) {
		Session session = sessionFactory.getCurrentSession();
		Client c = (Client) session.get(Client.class, id);
		return c;
	}

	@Override
	public List<Client> getAllClients() {
		Session session = sessionFactory.getCurrentSession();
		String req = "FROM Client c";
		Query query = session.createQuery(req);
		@SuppressWarnings("unchecked")
		List<Client> liste = query.list();
		return liste;
	}

	@Override
	public Client addClient(Client c) {
		Session session = sessionFactory.getCurrentSession();
		session.save(c);
		return c;
	}
}
