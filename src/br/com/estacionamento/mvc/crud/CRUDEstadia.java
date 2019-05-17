package br.com.estacionamento.mvc.crud;

import java.util.ArrayList;

import javax.persistence.Query;

import br.com.estacionamento.mvc.model.persistent_object.POEstadia;

public class CRUDEstadia extends AbstractCRUD {
	
	@Override
	public void insert(Object o) {
		POEstadia po = (POEstadia) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.persist(po);
		super.entityManager.getTransaction().commit();
		super.close();
		
	}
	
	@Override
	public void update(Object o) {
		POEstadia po = (POEstadia) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.merge(po);
		super.entityManager.getTransaction().commit();
		super.close();
		
	}
	
	@Override
	public void delete(Object o) {
		POEstadia po = (POEstadia) o;
		super.open();
		super.entityManager.getTransaction().begin();
		po = super.entityManager.find(POEstadia.class, po.getIdEstadia());
		super.entityManager.remove(po);
		super.entityManager.getTransaction().commit();
		super.close();
	}
	
	@Override
	public ArrayList<POEstadia> select(String statement) {
		
		if(statement == null || statement.equals("")){
			statement = "SELECT o FROM POEstado o";
		}
		super.open();
		Query query = super.entityManager.createQuery(statement);
		ArrayList<POEstadia> set = (ArrayList<POEstadia>) query.getResultList();
		super.close();
		return set;
		
	}
}
