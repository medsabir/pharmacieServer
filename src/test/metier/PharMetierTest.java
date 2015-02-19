package test.metier;

import junit.framework.Assert;
import metier.PharMetier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import dao.Pharmacie;

@SuppressWarnings("deprecation")
public class PharMetierTest {

	//une pharamcie au hasard dans la base de donnée
	Pharmacie pharmacie = new Pharmacie(1, "Grande Pharmacie d'Agadir",
			"av. Mly Abdallah , imm. M1 Agadir", 30.42056570185922,
			-9.597158908843994, "Tél.: 05 28 84 79 52", false);

	XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource(
			"spring-beans.xml"));
	PharMetier phm = (PharMetier) beanFactory.getBean("metier");

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		phm = null;
		pharmacie=null;
	}

	@Test
	public void testListdesPharmacies() {
		Assert.assertEquals(187, phm.ListdesPharmacies().size());
	}

	@Test
	public void testListdesPharmaciesenGarde() {
		Assert.assertEquals(13, phm.ListdesPharmaciesenGarde().size());
	}

	@Test
	public void testListdesPharmaciesProche() {
		
		//(30.42056570185921,-9.597158908843993) les coordonnées le plus proche de la pharmacie au dessus 
		
		//test de nom
		Assert.assertEquals(pharmacie.getNom(), phm.ListdesPharmaciesProche(
				30.42056570185921, -9.597158908843993).getNom());
		
		//test de l'adresse
		Assert.assertEquals(pharmacie.getAdresse(), phm.ListdesPharmaciesProche(
				30.42056570185921, -9.597158908843993).getAdresse());
		
		//test de numéro
		Assert.assertEquals(pharmacie.getNum(), phm.ListdesPharmaciesProche(
				30.42056570185921, -9.597158908843993).getNum());
		
		//test de le garde
		Assert.assertEquals(pharmacie.getGarde(), phm.ListdesPharmaciesProche(
				30.42056570185921, -9.597158908843993).getGarde());
		
		//test de latitude
		Assert.assertEquals(pharmacie.getLat(), phm.ListdesPharmaciesProche(
				30.42056570185921, -9.597158908843993).getLat());
		
		//test de longitude
		Assert.assertEquals(pharmacie.getLog(), phm.ListdesPharmaciesProche(
				30.42056570185921, -9.597158908843993).getLog());
	}

}
