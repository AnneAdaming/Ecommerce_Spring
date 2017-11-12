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
import fr.adaming.model.Panier;

@Aspect
public class MailAOP {
	@AfterReturning(pointcut="execution(* fr.adaming.service.IPanierService.envoyerMail(..))", returning="result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		System.out.println("MailAOP -> args :");
		Object[] args = joinPoint.getArgs();
		Panier panier = (Panier) args[0];
		Client client = (Client) args[1];
		System.out.println("panier : " + panier);
		System.out.println("client : " + client);
//		final String username = "thezadzad@gmail.com";
//		final String password = "adaming44";
//		Properties props = new Properties();
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.port", "587");
//		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//		Session session = Session.getInstance(props, new Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(username, password);
//			}
//		});
//		try {
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(username));
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress("h.boizard@laposte.net"));
//			message.setSubject("Commande Ecommerce");
//			StringBuilder sb = new StringBuilder();
//			String nom = "XXXX";
//			sb.append("Mme/Mr. " + nom + ",\n\n");
//			sb.append("Vous avez passé une commande pour :\n");
//			String[] items = {"item 1", "item 2", "item 3"};
//			for (int i=0; i<items.length; i++) {
//				sb.append("  - " + items[i] + "\n");
//			}
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(new Date());
//			calendar.add(Calendar.DAY_OF_YEAR, 12);
//			sb.append("\nLa date de réception est prévue au " + calendar.getTime());
//			message.setText(sb.toString());
//			Transport.send(message);
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
	}
}
