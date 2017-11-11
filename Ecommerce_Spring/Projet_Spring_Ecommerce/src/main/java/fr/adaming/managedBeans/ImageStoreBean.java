package fr.adaming.managedBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

@ManagedBean
@RequestScoped
public class ImageStoreBean {

	private UploadedFile file;

	// Store file in the database
	public void storeImage() {
		// Create connection
		try {
			// Load driver
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_images?useSSL=false", "root", "root");
			PreparedStatement statement = connection.prepareStatement("INSERT INTO images (image) VALUES (?)");
			statement.setBinaryStream(1, file.getInputstream());
			statement.executeUpdate();
			connection.commit();
			connection.close();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Upload success", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Upload error", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, errorMsg);
		}

	}
	
	private void uploadImage(){
		
	}
	
	public UploadedFile getFile() {
		return file;
	}
	public void setFile(UploadedFile file) {
		this.file = file;
	}
}
