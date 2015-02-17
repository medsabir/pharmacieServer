package dao;

import java.util.List;

import org.hibernate.Session;

import util.HibernateUtil;

public class PharmImpl implements PharmDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Pharmacie> getAllPharmacies() {
		Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Pharmacie> pharmacies = session.createQuery("from Pharmacie").list();
		return pharmacies;
	}

}