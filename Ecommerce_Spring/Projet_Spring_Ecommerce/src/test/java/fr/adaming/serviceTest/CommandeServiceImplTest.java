package fr.adaming.serviceTest;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/application-context.xml"})
public class CommandeServiceImplTest {
	@Autowired
	private ICommandeService commandeService;
	@Autowired
	private IClientService clientService;
	
	@Test
	@Transactional
	@Rollback(true)
	public void testAddCommande() {
		Commande co = new Commande(new Date());
//		Client cl = clientService.getClientById(1);
//		commandeService.addCommande(co, cl);
	}
}
