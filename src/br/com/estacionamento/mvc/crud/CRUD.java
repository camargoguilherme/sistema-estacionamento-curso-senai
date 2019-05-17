package br.com.estacionamento.mvc.crud;

import java.util.List;

public interface CRUD {
	public void create(Object obj);
	public Object read(Object obj, int id);
	public Object update(Object obj);
	public void delete(Object obj);
	public List listAll(Object obj);
}
