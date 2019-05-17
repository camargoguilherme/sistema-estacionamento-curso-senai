package br.com.estacionamento.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.estacionamento.mvc.crud.CRUD;
import br.com.estacionamento.mvc.model.persistent_object.POEstado;

public class EstadoController implements CRUD{
	EntityManagerFactory emf = null;
	EntityManager em = null;
	Query query = null;

	protected void open(){
		emf = Persistence.createEntityManagerFactory("ESTACIONAMENTO_PU");
		em = emf.createEntityManager();
	}
	
	protected void close(){
		em.close();
		emf.close();
	}
	
	@Override
	public void create(Object obj) {
		this.open();
		em.getTransaction().begin();
		em.persist(obj);		
		em.getTransaction().commit();
		this.close();
	}

	@Override
	public Object read(Object obj, int id) {
		Object finded = new Object();
		this.open();
		em.getTransaction().begin();
		em.persist(obj);		
		em.getTransaction().commit();
		this.close();
		return finded;
	}

	@Override
	public Object update(Object obj) {
		Object merged = new Object();
		this.open();
		em.getTransaction().begin();
		em.persist(obj);		
		em.getTransaction().commit();
		this.close();
		return merged;
	}

	@Override
	public void delete(Object obj) {
		this.open();
		em.getTransaction().begin();
		em.persist(obj);		
		em.getTransaction().commit();
		this.close();
	}

	@Override
	public List listAll(Object obj) {
		List list = new ArrayList<>();
		this.open();
		
		this.close();
		
		return list;
	}

}
