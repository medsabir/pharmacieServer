package metier;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class Testmetier {

	public static void main(String[] args) {
		XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-beans.xml"));
		IMetier metier = (IMetier) beanFactory.getBean("metier");
		System.out.println(metier.ListdesPharmaciesProche(30.4071176, -9.5704624));
	}

}
