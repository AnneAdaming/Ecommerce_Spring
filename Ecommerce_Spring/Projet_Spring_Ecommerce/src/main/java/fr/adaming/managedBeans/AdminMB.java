package fr.adaming.managedBeans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Admin;
import fr.adaming.service.IAdminService;

@ManagedBean(name="adminMB")
@RequestScoped
public class AdminMB implements Serializable {
	private static final long serialVersionUID = 1L;

	// Attributs
	@ManagedProperty(value="#{adminService}")
	private IAdminService adminService;
	private Admin admin;
	
	// Constructeur
	public AdminMB() {
		super();
	}
	@PostConstruct
	private void init() {
		this.admin = new Admin();
	}
	
	// Getters / Setters
	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	// Methodes
	public String login() {
		Admin adminSession = adminService.exists(this.admin);
		if (adminSession != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin", adminSession);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("logged", true);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "home.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("mail ou mdp invalide"));
			return "login.xhtml";
		}
	}
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "home.xhtml";
	}
	public String addAdmin() {
		System.out.println("Ajout admin : " + adminService.addAdmin(this.admin));
		return "home.xhtml";
	}
}
