package fr.adaming.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.aop.MethodBeforeAdvice;

public class PdfBeforeAdvice implements MethodBeforeAdvice {
	@Override
	public void before(Method methode, Object[] args, Object cible)
			throws Throwable {
		
		System.out.println("--Je suis avant l'exécution de la méthode "+methode.getName()+ " invoquée avec les paramètres "+Arrays.toString(args));
	}
}
