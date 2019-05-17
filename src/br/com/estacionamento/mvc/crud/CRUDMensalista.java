package br.com.estacionamento.mvc.crud;

import java.util.ArrayList;

import javax.persistence.Query;

import br.com.estacionamento.mvc.model.persistent_object.POMensalista;

public class CRUDMensalista extends AbstractCRUD {
	
	@Override
	public void insert(Object o) {
		POMensalista po = (POMensalista) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.persist(po);
		super.entityManager.getTransaction().commit();
		super.close();
		
	}
	
	@Override
	public void update(Object o) {
		POMensalista po = (POMensalista) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.merge(po);
		super.entityManager.getTransaction().commit();
		super.close();
		
	}
	
	@Override
	public void delete(Object o) {
		POMensalista po = (POMensalista) o;
		super.open();
		super.entityManager.getTransaction().begin();
		po = super.entityManager.find(POMensalista.class, po.getIdMensalista());
		super.entityManager.remove(po);
		super.entityManager.getTransaction().commit();
		super.close();
	}
	
	@Override
	public ArrayList<POMensalista> select(String statement) {
		
		if(statement == null || statement.equals("")){
			statement = "SELECT o FROM POMensalista o";
		}
		super.open();
		Query query = super.entityManager.createQuery(statement);
		ArrayList<POMensalista> set = (ArrayList<POMensalista>) query.getResultList();
		super.close();
		return set;
		
	}
}
