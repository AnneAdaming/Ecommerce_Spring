package fr.adaming.aop;

import java.io.IOException;

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
import fr.adaming.model.Panier;

@Aspect
public class PdfAOP {
	@AfterReturning(pointcut="execution(* fr.adaming.service.IPanierService.exporterPdf(..))", returning="result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		System.out.println("PdfAOP -> args :");
		Object[] args = joinPoint.getArgs();
		Panier panier = (Panier) args[0];
		Client client = (Client) args[1];
		System.out.println("panier : " + panier);
		System.out.println("client : " + client);
//		PDDocument document = new PDDocument();
//		PDPage page = new PDPage();
//		document.addPage(page);
//		PDFont font = PDType1Font.HELVETICA_BOLD;
//		PDPageContentStream contentStream;
//		try {
//			contentStream = new PDPageContentStream(document, page);
//			contentStream.beginText();
//			contentStream.setFont(font, 20);
//			contentStream.moveTextPositionByAmount(50, 700);
//			contentStream.drawString("Hello World");
//			contentStream.moveTextPositionByAmount(0, -100);
//			contentStream.drawString("Hello World 2");
//			contentStream.moveTextPositionByAmount(0, -100);
//			contentStream.drawString("Hello World 3");
//			contentStream.moveTextPositionByAmount(0, -100);
//			contentStream.drawString("Hello World 4");
//			contentStream.endText();
//			contentStream.close();
//			document.save("C:/Users/inti/Documents/HelloWorld.pdf");
//			document.close();
//		} catch (IOException | COSVisitorException e) {
//			e.printStackTrace();
//		}
	}
}
