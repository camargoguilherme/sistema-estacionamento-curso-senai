package br.com.estacionamento.mvc.crud;

import java.util.ArrayList;

import javax.persistence.Query;

import br.com.estacionamento.mvc.model.persistent_object.POCidade;

public class CRUDCidade extends AbstractCRUD {
	
	@Override
	public void insert(Object o) {
		POCidade po = (POCidade) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.persist(po);
		super.entityManager.getTransaction().commit();
		super.close();
		
	}
	
	@Override
	public void update(Object o) {
		POCidade po = (POCidade) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.merge(po);
		super.entityManager.getTransaction().commit();
		super.close();
		
	}
	
	@Override
	public void delete(Object o) {
		POCidade po = (POCidade) o;
		super.open();
		super.entityManager.getTransaction().begin();
		po = super.entityManager.find(POCidade.class, po.getIdCidade());
		super.entityManager.remove(po);
		super.entityManager.getTransaction().commit();
		super.close();
	}
	
	@Override
	public ArrayList<POCidade> select(String statement) {
		
		if(statement == null || statement.equals("")){
			statement = "SELECT o FROM POCidade o";
		}
		super.open();
		Query query = super.entityManager.createQuery(statement);
		ArrayList<POCidade> set = (ArrayList<POCidade>) query.getResultList();
		super.close();
		return set;
		
	}
}