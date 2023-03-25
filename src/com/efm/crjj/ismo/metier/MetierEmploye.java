package com.efm.crjj.ismo.metier;

import java.util.List;

import com.efm.crjj.ismo.dao.DaoEmploye;
import com.efm.crjj.ismo.dao.IDao;
import com.efm.crjj.ismo.model.Employe;

public class MetierEmploye implements IMetier<Employe> {
	
	IDao<Employe> dao = new DaoEmploye();

	@Override
	public List<Employe> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public Employe getOne(int id) {
		// TODO Auto-generated method stub
		return dao.getOne(id);
	}

	@Override
	public boolean save(Employe obj) {
		// TODO Auto-generated method stub
		return dao.save(obj);
	}

	@Override
	public boolean delete(Employe obj) {
		// TODO Auto-generated method stub
		return dao.delete(obj);
	}

}
