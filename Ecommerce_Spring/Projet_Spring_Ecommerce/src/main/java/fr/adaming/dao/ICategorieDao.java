package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Categorie;

public interface ICategorieDao {
	public Categorie addCategorie(Categorie c);
	public List<Categorie> getAllCategories();
	public Categorie getCategorieById(long id);
	public Categorie updateCategorie(Categorie c);
	public Categorie deleteCategorie(Categorie c);
	
}
