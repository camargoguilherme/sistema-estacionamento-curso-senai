package br.com.estacionamento.mvc.crud;

import java.util.ArrayList;

import javax.persistence.Query;

import br.com.estacionamento.mvc.model.persistent_object.POEstado;

public class CRUDEstado extends AbstractCRUD {
	
	@Override
	public void insert(Object o) {
		POEstado po = (POEstado) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.persist(po);
		super.entityManager.getTransaction().commit();
		super.close();
		
	}
	
	@Override
	public void update(Object o) {
		POEstado po = (POEstado) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.merge(po);
		super.entityManager.getTransaction().commit();
		super.close();
		
	}
	
	@Override
	public void delete(Object o) {
		POEstado po = (POEstado) o;
		super.open();
		super.entityManager.getTransaction().begin();
		po = super.entityManager.find(POEstado.class, po.getIdEstado());
		super.entityManager.remove(po);
		super.entityManager.getTransaction().commit();
		super.close();
	}
	
	@Override
	public ArrayList<POEstado> select(String statement) {
		
		if(statement == null || statement.equals("")){
			statement = "SELECT o FROM POEstado o";
		}
		super.open();
		Query query = super.entityManager.createQuery(statement);
		ArrayList<POEstado> set = (ArrayList<POEstado>) query.getResultList();
		super.close();
		return set;
		
	}
}
