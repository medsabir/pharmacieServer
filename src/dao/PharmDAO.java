package dao;

import java.util.List;

public interface PharmDAO {
	
	public void addPharmacie(Pharmacie p);
	public Pharmacie getPharmacieById(int id);
	public void deletePharmacie(int id);
	public void deleteAll();
	public List<Pharmacie> getAllPharmacies();
//	public void updatePharmacie(Long id);

}
