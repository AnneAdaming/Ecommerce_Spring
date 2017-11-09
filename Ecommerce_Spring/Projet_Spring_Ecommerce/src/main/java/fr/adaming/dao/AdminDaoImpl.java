package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Admin;

@Repository
public class AdminDaoImpl implements IAdminDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	public Admin exists(Admin a) {
		Session session = sessionFactory.getCurrentSession();
		String req = "FROM Admin a WHERE a.email=:pEmail AND a.mdp=:pMdp";
		Query query = session.createQuery(req);
		query.setParameter("pEmail", a.getEmail());
		query.setParameter("pMdp", a.getMdp());
		Admin aOut = (Admin) query.uniqueResult();
		return aOut;
	}

	@Override
	public Admin addAdmin(Admin a) {
		Session session = sessionFactory.getCurrentSession();
		session.save(a);
		return a;
	}
}
