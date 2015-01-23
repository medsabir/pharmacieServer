package metier;

import java.util.List;

import dao.Pharmacie;

public interface IMetier {

	public List<Pharmacie> ListdesPharmacies();
	public List<Pharmacie> ListdesPharmaciesenGarde();
	public List<Pharmacie> ListdesPharmaciesProche(Double lat,Double log);
	
}
