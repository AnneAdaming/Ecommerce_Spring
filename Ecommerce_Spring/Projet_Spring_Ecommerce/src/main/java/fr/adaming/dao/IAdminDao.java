package fr.adaming.dao;

import fr.adaming.model.Admin;

public interface IAdminDao {
	public Admin exists(Admin a);
	public Admin addAdmin(Admin a);
}
