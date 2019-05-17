package br.com.estacionamento.mvc.model.persistent_object;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.estacionamento.mvc.controller.EstadoController;
import br.com.estacionamento.mvc.crud.CRUDCidade;
import br.com.estacionamento.mvc.crud.CRUDEstado;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

public class Teste {

	public static void main(String[] args) {
		CRUDEstado crudEstado = new CRUDEstado();
		CRUDCidade crudCidade = new CRUDCidade();
		
		ArrayList<POEstado> estadoResult = crudEstado.select("SELECT o FROM POEstado o WHERE"
				+ " o.idEstado = 1");
		
		POEstado estado = (POEstado) estadoResult.get(0);

		POCidade cidade = new POCidade();
		cidade.setNomeCidade("Rolandia");
		cidade.setEstadoCidade(estado);
		cidade.setStatusCidade(EnumStatus.ATIVO);
		
		crudCidade.insert(cidade);
		System.out.println(cidade.getIdCidade());
		
		
		
		/*
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ESTACIONAMENTO_PU");
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("SELECT o FROM POEstado o");
		ArrayList<POEstado> list = (ArrayList<POEstado>) q.getResultList();
		for(POEstado e: list){
			System.out.printf("{\n\tID: %d\n\tNOME: %s\n\tSIGLA: %s\n}",e.getIdEstado(), e.getNomeEstado(), e.getSiglaEstado());
		}
		//Operação
		
		em.getTransaction().begin();
		//em.persist(estado);		
		//em.getTransaction().commit();
		*/
		/*
		//find
		estado = em.find(POEstado.class, 2);
		System.out.println("\nFIND");
		System.out.printf("{\n\tID: %d\n\tNOME: %s\n\tSIGLA: %s\n}",estado.getIdEstado(), estado.getNomeEstado(), estado.getSiglaEstado());
		
		//merge
		estado.setIdEstado(5);
		estado.setNomeEstado("Santa Catarina");
		estado.setSiglaEstado("SC");
		estado = em.merge(estado);
		System.out.println("\nMERGE");
		System.out.printf("{\n\tID: %d\n\tNOME: %s\n\tSIGLA: %s\n}",estado.getIdEstado(), estado.getNomeEstado(), estado.getSiglaEstado());
		
		//remove
		em.remove(estado);
		System.out.println("\nREMOVE");
		System.out.printf("{\n\tID: %d\n\tNOME: %s\n\tSIGLA: %s\n}",estado.getIdEstado(), estado.getNomeEstado(), estado.getSiglaEstado());
		
		
		//HQL
		*/
	}

}
