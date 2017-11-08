package fr.adaming.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="commandes")
public class Commande implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_co")
	private long id;
	@Temporal(TemporalType.DATE)
	@Column(name="date_co")
	private Date date;
	@ManyToOne
	@JoinColumn(name="id_cl", referencedColumnName="id_cl")
	private Client client;
	@OneToMany(mappedBy="commande")
	private List<LigneCommande> listeLigneCommande;

	// Constructeurs
	public Commande() {
		super();
	}
	public Commande(Date date) {
		super();
		this.date = date;
	}
	public Commande(long id, Date date) {
		super();
		this.id = id;
		this.date = date;
	}
	
	// Getters / Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
//	public List<LigneCommande> getListeLigneCommande() {
//		return listeLigneCommande;
//	}
//	public void setListeLigneCommande(List<LigneCommande> listeLigneCommande) {
//		this.listeLigneCommande = listeLigneCommande;
//	}
	
	@Override
	public String toString() {
		return "Commande [id=" + id + ", date=" + date + "]";
	}
}
