package services;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import dao.Pharmacie;
import metier.IMetier;

@WebService(endpointInterface = "services.IServicesPharmacie")
public class ServicesPharmacie implements IServicesPharmacie {


	XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-beans.xml"));
	IMetier servmetier = (IMetier) beanFactory.getBean("metier");
//	IMetier servmetier;

	/* (non-Javadoc)
	 * @see services.IServicesPharmacie#ListdesPharmacies()
	 */
	@Override
	public List<Pharmacie> ListdesPharmacies() {
		return servmetier.ListdesPharmacies();
	}

	/* (non-Javadoc)
	 * @see services.IServicesPharmacie#ListdesPharmaciesenGarde()
	 */
	@Override
	public List<Pharmacie> ListdesPharmaciesenGarde() {
		return servmetier.ListdesPharmaciesenGarde();
	}

	/* (non-Javadoc)
	 * @see services.IServicesPharmacie#ListdesPharmaciesProche(java.lang.String)
	 */
	@Override
	public Pharmacie PharmacieProche(Double lat,Double log){
		return servmetier.ListdesPharmaciesProche(lat,log); 
	}

	
	public void setServmetier(IMetier servmetier) {
		this.servmetier = servmetier;
	}
	
	// wsimport -keep -s src url


}
