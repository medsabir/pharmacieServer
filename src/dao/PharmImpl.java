package dao;

import java.util.List;

import org.hibernate.Session;

import util.HibernateUtil;

public class PharmImpl implements PharmDAO {

	@Override
	public void addPharmacie(Pharmacie p) {
		Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
		
	}

	@Override
	public Pharmacie getPharmacieById(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Pharmacie p = (Pharmacie) session.load(Pharmacie.class, id);
		return p;
	}
	
	@Override
	public void deletePharmacie(int id) {
		Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(getPharmacieById(id));
		session.getTransaction().commit();
	}
	
	@Override
	public void deleteAll() {
		Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.createQuery("TRUNCATE TABLE `pharmacie`");
		session.getTransaction().commit();
	}

	@Override
	public List<Pharmacie> getAllPharmacies() {
		Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Pharmacie> pharmacies = session.createQuery("from Pharmacie").list();
		return pharmacies;
	}

//	@Override
//	public void updatePharmacie(Long id) {
//		Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		 session.update("Pharmacie.class",id);
//	    session.getTransaction().commit();
//	
//	}



	

}
