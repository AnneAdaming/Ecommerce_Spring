package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.LigneCommande;

@Repository
public class LigneCommandeDaoImpl implements ILigneCommandeDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	public LigneCommande getLigneCommandeById(long id) {
		Session session = sessionFactory.getCurrentSession();
		LigneCommande lc = (LigneCommande) session.get(LigneCommande.class, id);
		return lc;
	}

	@Override
	public List<LigneCommande> getAllLigneCommandes() {
		Session session = sessionFactory.getCurrentSession();
		String req = "FROM LigneCommande lc";
		Query query = session.createQuery(req);
		@SuppressWarnings("unchecked")
		List<LigneCommande> liste = query.list();
		return liste;
	}

	@Override
	public LigneCommande addLigneCommande(LigneCommande lc) {
		Session session = sessionFactory.getCurrentSession();
		session.save(lc);
		return lc;
	}
}
