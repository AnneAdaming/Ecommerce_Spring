package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Categorie;

public interface ICategorieService {
	public Categorie addCategorie(Categorie c);
	public List<Categorie> getAllCategories();
	public Categorie getCategorieById(long id);
	public Categorie updateCategorie(Categorie c);
	public Categorie deleteCategorie(Categorie c);
}
