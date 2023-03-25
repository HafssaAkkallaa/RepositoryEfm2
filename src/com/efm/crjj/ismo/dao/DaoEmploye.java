package com.efm.crjj.ismo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.efm.crjj.ismo.model.Employe;

import com.utils.HibernateUtils;


public class DaoEmploye implements IDao<Employe> {

	@Override
	public List<Employe> getAll() {
		Session s = HibernateUtils.getSessionfactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		List<Employe> employes = s.createQuery("from Employe").getResultList();
		t.commit();
		s.close();
		return employes;
	}

	@Override
	public Employe getOne(int id) {
		Session s = HibernateUtils.getSessionfactory().getCurrentSession();
		Transaction t = s.beginTransaction();
        Employe em=s.get(Employe.class, id);
		t.commit();
		s.close();
		return em;
	}

	@Override
	public boolean save(Employe obj) {
		try {
			Session session = HibernateUtils.getSessionfactory().getCurrentSession();
			Transaction t = session.beginTransaction();

			Object o = session.save(obj);

			System.out.println(o);

			t.commit();
			session.close();

			if (o == null)
				return false;
			else
				return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Employe obj) {
		try {
			Session s = HibernateUtils.getSessionfactory().getCurrentSession();
			Transaction t = s.beginTransaction();

			s.delete(obj);

			t.commit();
			s.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
