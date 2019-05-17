package br.com.estacionamento.mvc.crud;

import java.util.ArrayList;

import javax.persistence.Query;

import br.com.estacionamento.mvc.model.persistent_object.POTelefone;

public class CRUDTelefone extends AbstractCRUD {
	
	@Override
	public void insert(Object o) {
		POTelefone po = (POTelefone) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.persist(po);
		super.entityManager.getTransaction().commit();
		super.close();
		
	}
	
	@Override
	public void update(Object o) {
		POTelefone po = (POTelefone) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.merge(po);
		super.entityManager.getTransaction().commit();
		super.close();
		
	}
	
	@Override
	public void delete(Object o) {
		POTelefone po = (POTelefone) o;
		super.open();
		super.entityManager.getTransaction().begin();
		po = super.entityManager.find(POTelefone.class, po.getIdTelefone());
		super.entityManager.remove(po);
		super.entityManager.getTransaction().commit();
		super.close();
	}
	
	@Override
	public ArrayList<POTelefone> select(String statement) {
		
		if(statement == null || statement.equals("")){
			statement = "SELECT o FROM POTelefone o";
		}
		super.open();
		Query query = super.entityManager.createQuery(statement);
		ArrayList<POTelefone> set = (ArrayList<POTelefone>) query.getResultList();
		System.out.println(query.getResultList().toString());
		super.close();
		return set;
		
	}
}
