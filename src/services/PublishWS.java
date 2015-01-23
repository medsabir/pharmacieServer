package services;

import javax.xml.ws.Endpoint;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PublishWS {
	
//	public static ClassPathXmlApplicationContext context;

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
		IServicesPharmacie pharmacieService = (IServicesPharmacie) context.getBean("service");
		context.close();
		Endpoint.publish("http://localhost:3597/Pharmacies", pharmacieService);
		System.out.println("The service has been published with success!");
		
		
//		ServicesPharmacie sp = new ServicesPharmacie();
//		System.out.println(sp.ListdesPharmacies());
//		System.out.println("------------------------------------------------------------------------------------------------");
//		System.out.println(sp.ListdesPharmaciesenGarde());
//		System.out.println("------------------------------------------------------------------------------------------------");
//		System.out.println(sp.ListdesPharmaciesProche(30.4, -9.5));
		
		
//		context = new ClassPathXmlApplicationContext("spring-beans.xml"); 
//		try {
//		ServicesPharmacie pharmacieService = (ServicesPharmacie) context.getBean("service");
//		System.out.println(pharmacieService.ListdesPharmacies());
//		}catch (final Exception e) {
//			e.printStackTrace();
//			System.exit(1);
//		}
		}
	}
