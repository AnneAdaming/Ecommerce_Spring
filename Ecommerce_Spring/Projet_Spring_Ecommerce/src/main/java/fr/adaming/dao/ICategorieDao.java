package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Categorie;

public interface ICategorieDao {
	public Categorie getCategorieById(long id);
	public List<Categorie> getAllCategories();
	public Categorie addCategorie(Categorie c);
	public Categorie updateCategorie(Categorie c);
	public void deleteCategorie(Categorie c);
}
