package br.com.estacionamento.mvc.crud;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractCRUD {
	protected EntityManagerFactory entityManagerFactory = null;
	protected EntityManager entityManager = null;
	
	public void open(){
		this.entityManagerFactory = Persistence.createEntityManagerFactory("ESTACIONAMENTO_PU");
		this.entityManager = entityManagerFactory.createEntityManager();
	}
	
	public void close(){
		this.entityManager.close();
		this.entityManagerFactory.close();
	}
	
	public abstract void insert(Object obj);
	public abstract void update(Object obj);
	public abstract void delete(Object obj);
	public abstract ArrayList<?> select(String statement);
	
	
}
