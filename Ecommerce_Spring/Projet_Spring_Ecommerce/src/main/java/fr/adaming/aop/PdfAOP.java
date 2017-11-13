package fr.adaming.aop;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import fr.adaming.model.Client;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;

@Aspect
public class PdfAOP {
	@AfterReturning(pointcut="execution(* fr.adaming.service.IPanierService.exporterPdf(..))", returning="result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		System.out.println("PdfAOP -> args :");
		Object[] args = joinPoint.getArgs();
		Panier panier = (Panier) args[0];
		Client client = (Client) args[1];
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);
		PDFont font = PDType1Font.HELVETICA_BOLD;
		PDPageContentStream contentStream;
		try {
			contentStream = new PDPageContentStream(document, page);
			contentStream.beginText();
			contentStream.setFont(font, 20);
			contentStream.moveTextPositionByAmount(50, 700);
			contentStream.drawString("Date : " + new Date());
			contentStream.moveTextPositionByAmount(0, -50);
			contentStream.drawString("Client : " + client.getPrenom() + " " + client.getNom());
			contentStream.moveTextPositionByAmount(0, -100);
			contentStream.drawString("Produits :");
			for (LigneCommande l : panier.getListeLignesCommande()) {
				contentStream.moveTextPositionByAmount(0, -30);
				if (l.getQuantite() == 1) {
					contentStream.drawString("  - " + l.getQuantite() + " " + l.getProduit().getDescription());
				} else {
					contentStream.drawString("  - " + l.getQuantite() + " " + l.getProduit().getDescription() + "(s)");
				}
			}
			contentStream.moveTextPositionByAmount(0, -50);
			contentStream.drawString("Total : " + panier.getTotal());
			contentStream.endText();
			contentStream.close();
			document.save("C:/Users/inti/Documents/Commande_" + panier.getListeLignesCommande().get(0).getCommande().getId() + ".pdf");
			document.close();
		} catch (IOException | COSVisitorException e) {
			e.printStackTrace();
		}
	}
}
