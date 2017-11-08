package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Repository
public class ProduitDaoImpl implements IProduitDao{
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Produit> getAllProduit() {
		Session session = sessionFactory.getCurrentSession();
		String req="FROM Produit p";
		Query query=session.createQuery(req);
		List<Produit> liste=query.list();
		return liste;
	}

	@Override
	public List<Produit> getAllProduitByCategorie(Categorie c) {
		Session session = sessionFactory.getCurrentSession();
		String req="FROM Produit p WHERE p.categorie.id=:pId";
		Query query=session.createQuery(req);
		query.setParameter("pId", c.getId());
		List<Produit> liste=query.list();
		return liste;
	}

	@Override
	public Produit getProduitById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produit addProduit(Produit p, Categorie c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduit(Produit p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Produit modifyProduit(Produit p) {
		// TODO Auto-generated method stub
		return null;
	}

}
