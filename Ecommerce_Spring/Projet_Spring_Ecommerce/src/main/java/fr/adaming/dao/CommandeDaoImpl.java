package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Repository
public class CommandeDaoImpl implements ICommandeDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	public Commande getCommandeById(long id) {
		Session session = sessionFactory.getCurrentSession();
		Commande c = (Commande) session.get(Commande.class, id);
		return c;
	}

	@Override
	public List<Commande> getAllCommandes() {
		Session session = sessionFactory.getCurrentSession();
		String req = "FROM Commande c";
		Query query = session.createQuery(req);
		@SuppressWarnings("unchecked")
		List<Commande> liste = query.list();
		return liste;
	}

	@Override
	public Commande addCommande(Commande co) {
		Session session = sessionFactory.getCurrentSession();
		session.save(co);
		return co;
	}
}
