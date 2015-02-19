package test.dao;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import dao.PharmDAO;

@SuppressWarnings("deprecation")
public class PharmImplTest {
	
	XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-beans.xml"));
	PharmDAO ph = (PharmDAO) beanFactory.getBean("dao");

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		ph = null;
	}

	@Test
	public void testGetAllPharmacies() {
		//le nombre des lignes dans la table de donnée = 187
		Assert.assertEquals(187, ph.getAllPharmacies().size());
	}

}
