package br.com.estacionamento.mvc.model.persistent_object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.estacionamento.mvc.crud.CRUD;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

@Entity
@Table(name="TB_ESTADO")

public class POEstado {
	
//	+------------------+-------------------------+------+-----+---------+----------------+
//	| Field            | Type                    | Null | Key | Default | Extra          |
//	+------------------+-------------------------+------+-----+---------+----------------+
//	| TB_ESTADO_ID     | int(11)                 | NO   | PRI | NULL    | auto_increment |
//	| TB_ESTADO_NOME   | varchar(50)             | NO   |     | NULL    |                |
//	| TB_ESTADO_SIGLA  | varchar(2)              | NO   |     | NULL    |                |
//	| TB_ESTADO_STATUS | enum('ativo','inativo') | NO   |     | NULL    |                |
//	+------------------+-------------------------+------+-----+---------+----------------+
	@Id
	@GeneratedValue
	@Column(name="TB_ESTADO_ID", nullable=false)
	private int idEstado;
	
	@Column(name="TB_ESTADO_NOME", nullable=false, length=50)
	private String nomeEstado;
	
	@Column(name="TB_ESTADO_SIGLA", nullable=false, length=2)
	private String siglaEstado;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name="TB_ESTADO_STATUS", nullable=false)
	private EnumStatus status;
	
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	public String getNomeEstado() {
		return nomeEstado;
	}
	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}
	public String getSiglaEstado() {
		return siglaEstado;
	}
	public void setSiglaEstado(String siglaEstado) {
		this.siglaEstado = siglaEstado;
	}
	public EnumStatus getStatus() {
		return status;
	}
	public void setStatus(EnumStatus status) {
		this.status = status;
	}
	
	public JSONObject toJSON() throws JSONException{ 
		JSONObject json = new JSONObject();
		json.put("idEstado", this.idEstado);
		json.put("nomeEstado", this.nomeEstado);
		json.put("siglaEstado", this.siglaEstado);
		json.put("status", this.status.getStatus());
		return json;
	}
}
