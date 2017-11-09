package fr.adaming.service;

import fr.adaming.model.Admin;

public interface IAdminService {
	public Admin exists(Admin a);
	public Admin addAdmin(Admin a);
}
