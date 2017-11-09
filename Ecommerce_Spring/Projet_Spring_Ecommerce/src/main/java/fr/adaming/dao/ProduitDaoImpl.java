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
	public Produit getProduitById(long id) {
		Session session = sessionFactory.getCurrentSession();
		Produit p = (Produit) session.get(Produit.class, id);
		return p;
	}
	
	@Override
	public List<Produit> getAllProduits() {
		Session session = sessionFactory.getCurrentSession();
		String req="FROM Produit p";
		Query query=session.createQuery(req);
		@SuppressWarnings("unchecked")
		List<Produit> liste=query.list();
		return liste;
	}

	@Override
	public List<Produit> getAllProduitsByCategorie(Categorie c) {
		Session session = sessionFactory.getCurrentSession();
		String req="FROM Produit p WHERE p.categorie.id=:pIdc";
		Query query=session.createQuery(req);
		query.setParameter("pIdc", c.getId());
		@SuppressWarnings("unchecked")
		List<Produit> liste=query.list();
		return liste;
	}

	@Override
	public Produit addProduit(Produit p) {
		Session session = sessionFactory.getCurrentSession();
		session.save(p);
		return p;
	}

	@Override
	public Produit updateProduit(Produit p) {
		Session session = sessionFactory.getCurrentSession();
		Produit pOut = (Produit) session.get(Produit.class, p.getId());
		pOut.setDesignation(p.getDesignation());
		pOut.setDescription(p.getDescription());
		pOut.setPrix(p.getPrix());
		pOut.setQuantite(p.getQuantite());
		pOut.setCategorie(p.getCategorie());
		session.saveOrUpdate(pOut);
		return pOut;
	}
	
	@Override
	public void deleteProduit(Produit p) {
		// TODO Auto-generated method stub
		
	}
}
