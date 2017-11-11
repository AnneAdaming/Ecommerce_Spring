package fr.adaming.managedBeans;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name="imageMB")
@ApplicationScoped
public class ImageManagedBean {
	@ManagedProperty(value="#{produitService}")
	IProduitService produitService;

	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}
	
	public StreamedContent getImage() throws IOException{
		FacesContext contexte=FacesContext.getCurrentInstance();
		if(contexte.getCurrentPhaseId()==PhaseId.RENDER_RESPONSE){
			return new DefaultStreamedContent();
		} else {
			// On récupère l'id du produit, sous forme de String
			String id=contexte.getExternalContext().getRequestParameterMap().get("id");
			
			// On récupère le produit associé à l'id
			Produit produit=new Produit();
			System.out.println("id : "+id);
			produit=produitService.getProduitById(Integer.parseInt(id));
			// On récupère l'image en byte
			byte[] image=produit.getImage();
			// On retourne l'image sous format ByteArrayInputStream
			return new DefaultStreamedContent(new ByteArrayInputStream(image));
		}
		
	}
	
}
