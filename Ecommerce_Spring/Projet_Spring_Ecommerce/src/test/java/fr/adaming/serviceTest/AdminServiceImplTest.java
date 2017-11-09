package fr.adaming.serviceTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.model.Admin;
import fr.adaming.service.IAdminService;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/application-context.xml"})
public class AdminServiceImplTest {
	@Autowired
	private IAdminService adminService;
	private Admin admin;
	
	@Before
	public void setUp() {
		this.admin = new Admin(1,"a@a","a");
	}
	
	@Test
	public void testExists() {
		Admin adminOut = adminService.exists(this.admin);
		assertNotNull(adminOut);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testAddAdmin() {
		//TODO
	}
}
