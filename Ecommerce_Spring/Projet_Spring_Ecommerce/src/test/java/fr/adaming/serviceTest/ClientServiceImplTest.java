package fr.adaming.serviceTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.model.Client;
import fr.adaming.service.IClientService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/application-context.xml"})
public class ClientServiceImplTest {
	@Autowired
	private IClientService clientService;

	@Test
	@Transactional
	@Rollback(true)
	public void testAddClient() {
		Client c  = new Client("Gilmour", "David", "Londres", "d@g", "02 41 32");
		clientService.addClient(c);
	}
}
