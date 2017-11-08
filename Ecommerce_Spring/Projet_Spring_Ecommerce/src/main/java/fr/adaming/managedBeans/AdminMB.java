package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import fr.adaming.model.Admin;
import fr.adaming.service.IAdminService;

@ManagedBean(name="adminMB")
@RequestScoped
public class AdminMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{adminService}")
	private IAdminService adminService;
	private Admin admin;
	
	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public String login() {
		System.out.println("Login");
		return "home.xhtml";
	}
}
