package fr.adaming.serviceTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/application-context.xml"})
public class ProduitServiceImplTest {
	@Autowired
	private IProduitService produitService;
	
	private Produit produit;

	@Before
	public void setUp(){
		this.produit=new Produit();
		produit.setId(1);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteProduit(){
		// On teste la destruction du produit 1
		int taille=produitService.getAllProduits().size();
		produitService.deleteProduit(produit);
		assertEquals(taille-1, produitService.getAllProduits().size());
	}

}
