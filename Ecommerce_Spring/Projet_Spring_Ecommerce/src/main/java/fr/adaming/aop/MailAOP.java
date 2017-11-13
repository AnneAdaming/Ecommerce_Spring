package fr.adaming.aop;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import fr.adaming.model.Client;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;

@Aspect
public class MailAOP {
	@AfterReturning(pointcut="execution(* fr.adaming.service.IPanierService.envoyerMail(..))", returning="result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		System.out.println("MailAOP -> args :");
		Object[] args = joinPoint.getArgs();
		Panier panier = (Panier) args[0];
		Client client = (Client) args[1];
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
			sb.append("Mme/Mr. " + client.getPrenom() + " " + client.getNom() + ",\n\n");
			sb.append("Vous avez passé une commande pour :\n");
			for (LigneCommande l : panier.getListeLignesCommande()) {
				sb.append("  - " + l.getQuantite() + " " + l.getProduit().getDescription() + "(s)\n");
			}
			sb.append("\nTotal : " + panier.getTotal());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_YEAR, 12);
			sb.append("\n\nLa date de réception est prévue au " + calendar.getTime());
			message.setText(sb.toString());
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
