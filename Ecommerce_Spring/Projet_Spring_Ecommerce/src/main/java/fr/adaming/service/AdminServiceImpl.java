package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IAdminDao;
import fr.adaming.model.Admin;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements IAdminService {
	@Autowired
	private IAdminDao adminDao;

	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}

	
	@Override
	public Admin exists(Admin a) {
		if (a==null) {
			System.out.println("Erreur lors de la vérification de validité de l'admin");
			return null;
		}
		return adminDao.exists(a);
	}

	@Override
	public Admin addAdmin(Admin a) {
		if (a==null) {
			System.out.println("Erreur lors de l'ajout de l'admin");
			return null;
		}
		return adminDao.addAdmin(a);
	}
}
