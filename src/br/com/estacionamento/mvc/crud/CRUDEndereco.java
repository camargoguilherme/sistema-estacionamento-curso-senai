package br.com.estacionamento.mvc.crud;

import java.util.ArrayList;

import javax.persistence.Query;

import br.com.estacionamento.mvc.model.persistent_object.POEndereco;

public class CRUDEndereco extends AbstractCRUD {
	
	@Override
	public void insert(Object o) {
		POEndereco po = (POEndereco) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.persist(po);
		super.entityManager.getTransaction().commit();
		super.close();
		
	}
	
	@Override
	public void update(Object o) {
		POEndereco po = (POEndereco) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.merge(po);
		super.entityManager.getTransaction().commit();
		super.close();
		
	}
	
	@Override
	public void delete(Object o) {
		POEndereco po = (POEndereco) o;
		super.open();
		super.entityManager.getTransaction().begin();
		po = super.entityManager.find(POEndereco.class, po.getIdEndereco());
		super.entityManager.remove(po);
		super.entityManager.getTransaction().commit();
		super.close();
	}
	
	@Override
	public ArrayList<POEndereco> select(String statement) {
		
		if(statement == null || statement.equals("")){
			statement = "SELECT o FROM POEndereco o";
		}
		super.open();
		Query query = super.entityManager.createQuery(statement);
		ArrayList<POEndereco> set = (ArrayList<POEndereco>) query.getResultList();
		super.close();
		return set;
		
	}
}
