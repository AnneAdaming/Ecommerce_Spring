package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;


@ManagedBean(name="testMB")
@RequestScoped
public class TestMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Constructeur
	public TestMB() {
		super();
	}

	// Methodes
	public String sendMail() {
		System.out.println("Send Mail");
		final String username = "thezadzad@gmail.com";
		final String password = "adaming44";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("h.boizard@laposte.net"));
			message.setSubject("Commande Ecommerce");
			StringBuilder sb = new StringBuilder();
			String nom = "XXXX";
			sb.append("Mme/Mr. " + nom + ",\n\n");
			sb.append("Vous avez passé une commande pour :\n");
			String[] items = {"item 1", "item 2", "item 3"};
			for (int i=0; i<items.length; i++) {
				sb.append("  - " + items[i] + "\n");
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_YEAR, 12);
			sb.append("\nLa date de réception est prévue au " + calendar.getTime());
			message.setText(sb.toString());
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return "home.xhtml";
	}

	public String exportPdf() {
		System.out.println("exportPdf()");
		return "home.xhtml";
	}
}
