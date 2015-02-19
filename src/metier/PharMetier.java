package metier;

import java.util.ArrayList;
import java.util.List;

import dao.PharmDAO;
import dao.Pharmacie;


public class PharMetier implements IMetier {

	PharmDAO phardao;

	@Override
	public List<Pharmacie> ListdesPharmacies() {
		return phardao.getAllPharmacies();
	}

	@Override
	public List<Pharmacie> ListdesPharmaciesenGarde() {
		List<Pharmacie> phGarde = phardao.getAllPharmacies();
		List<Pharmacie> pharmacies = new ArrayList<Pharmacie>();
		for (Pharmacie p : phGarde) {
			if (p.getGarde() == true) {
				pharmacies.add(p);
			}
		}
		return pharmacies;
	}

	@Override
	public Pharmacie ListdesPharmaciesProche(Double lat, Double log) {
		double min = 0;
		int a = 1;
		Pharmacie pha = new Pharmacie();
		List<Pharmacie> pharmacies = phardao.getAllPharmacies();
		for (Pharmacie p : pharmacies) {
			double distance = distance(lat, p.getLat(), log, p.getLog());
			while (a == 1) {
				min = distance;
				pha = p;
				a = 2;
			}
			if (distance < min) {
				min = distance;
				pha = p;
			}
		}
		return pha;
	}

	private double distance(double lat1, double lat2, double lon1, double lon2) {

		final int R = 6371; // Radius of the earth

		Double latDistance = deg2rad(lat2 - lat1);
		Double lonDistance = deg2rad(lon2 - lon1);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
				* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters
		return distance;
	}

	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	public void setPhardao(PharmDAO phardao) {
		this.phardao = phardao;
	}

}