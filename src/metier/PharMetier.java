package metier;

import java.util.ArrayList;
import java.util.List;

import dao.PharmDAO;
import dao.Pharmacie;

public class PharMetier implements IMetier{
	PharmDAO phardao;

	@Override
	public List<Pharmacie> ListdesPharmacies() {
		return phardao.getAllPharmacies();
	}

	@Override
	public List<Pharmacie> ListdesPharmaciesenGarde() {
		List<Pharmacie> phGarde = phardao.getAllPharmacies();
		List<Pharmacie> pharmacies = new ArrayList<Pharmacie>();
		for(Pharmacie p:phGarde){
			if(p.getGarde() == true){
				pharmacies.add(p);
			}
		}
		return pharmacies;
	}

	@Override
	public List<Pharmacie> ListdesPharmaciesProche(Double lat,Double log) {
		List<Pharmacie> pharmacies = phardao.getAllPharmacies();
		List<Pharmacie> list = new ArrayList<Pharmacie>();
		for(Pharmacie p:pharmacies){
			if(lat-0.01<p.getLat() && p.getLat()<lat+0.01 && log-0.01<p.getLog() && p.getLog()<log+0.01){
				list.add(p);
			}
		}
		return list;
	}

	public void setPhardao(PharmDAO phardao) {
		this.phardao = phardao;
	}
	
	

	
}
