package metier;

import java.util.List;

import dao.Pharmacie;

public interface IMetier {

	public List<Pharmacie> ListdesPharmacies();
	public List<Pharmacie> ListdesPharmaciesenGarde();
	public Pharmacie ListdesPharmaciesProche(Double lat,Double log);
	public double distance(double lat1, double lat2, double lon1, double lon2);
	
}
