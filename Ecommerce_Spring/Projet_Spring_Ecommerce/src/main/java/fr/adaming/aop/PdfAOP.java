package fr.adaming.aop;

import java.io.IOException;
import java.util.Arrays;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class PdfAOP {
	//@Before("execution(* fr.adaming.managedBeans.TestMB.exportPdf(..))")
	public void beforeExportPdf(JoinPoint joinPoint) {
		System.out.println("Before - " + joinPoint.getSignature().getName() + " :");
		System.out.println("  - params : " + Arrays.toString(joinPoint.getArgs()));
		System.out.println("  - target : " + joinPoint.getTarget());
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
			contentStream.drawString("Hello World");
			contentStream.moveTextPositionByAmount(0, -100);
			contentStream.drawString("Hello World 2");
			contentStream.moveTextPositionByAmount(0, -100);
			contentStream.drawString("Hello World 3");
			contentStream.moveTextPositionByAmount(0, -100);
			contentStream.drawString("Hello World 4");
			contentStream.endText();
			contentStream.close();
			document.save("C:/Users/inti/Documents/HelloWorld.pdf");
			document.close();
		} catch (IOException | COSVisitorException e) {
			e.printStackTrace();
		}
	}
	
	//@After("execution(* fr.adaming.managedBeans.TestMB.exportPdf(..))")
	public void afterExportPdf(JoinPoint pointJonction){
		System.out.println("after");
	}
}
