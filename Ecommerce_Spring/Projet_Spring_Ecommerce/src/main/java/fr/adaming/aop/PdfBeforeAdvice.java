package fr.adaming.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.aop.MethodBeforeAdvice;

public class PdfBeforeAdvice implements MethodBeforeAdvice {
	@Override
	public void before(Method methode, Object[] args, Object cible)
			throws Throwable {
		
		System.out.println("--Je suis avant l'ex�cution de la m�thode "+methode.getName()+ " invoqu�e avec les param�tres "+Arrays.toString(args));
	}
}
