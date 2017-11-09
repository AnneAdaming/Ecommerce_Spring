package fr.adaming.serviceTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/application-context.xml"})
public class LigneCommandeServiceImplTest {
	@Autowired
	private ILigneCommandeService ligneCommandeService;
	@Autowired
	private ICommandeService commandeService;
	@Autowired
	private IProduitService produitService;

	@Test
	@Transactional
	@Rollback(true)
	public void testAddLigneCommande() {
		LigneCommande lc = new LigneCommande(15, 12.2);
		Commande co = commandeService.getCommandeById(1);
		Produit pr = produitService.getProduitById(1);
		ligneCommandeService.addLigneCommande(lc, co, pr);
	}
}
