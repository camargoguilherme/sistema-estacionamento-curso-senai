package br.com.estacionamento.mvc.model.persistent_object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;


@Entity
@Table(name = "TB_CIDADE")
public class POCidade {
//	+------------------+-------------------------+------+-----+---------+----------------+
//	| Field            | Type                    | Null | Key | Default | Extra          |
//	+------------------+-------------------------+------+-----+---------+----------------+
//	| TB_CIDADE_ID     | int(11)                 | NO   | PRI | NULL    | auto_increment |
//	| TB_CIDADE_NOME   | varchar(50)             | NO   |     | NULL    |                |
//	| TB_CIDADE_STATUS | enum('ativo','inativo') | NO   |     | NULL    |                |
//	| TB_ESTADO_ID     | int(11)                 | NO   | MUL | NULL    |                |
//	+------------------+-------------------------+------+-----+---------+----------------+
	@Id
	@GeneratedValue
	@Column(name = "TB_CIDADE_ID", nullable = false)
	private int idCidade;
	
	@Column(name = "TB_CIDADE_NOME", nullable = false)
	private String nomeCidade;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TB_CIDADE_STATUS", nullable = false)
	private EnumStatus statusCidade;
	
	@OneToOne
	@JoinColumn(name = "TB_ESTADO_ID", nullable = false)
	private POEstado estadoCidade;
	
	
	public int getIdCidade() {
		return idCidade;
	}
	public void setIdCidade(int idCidade) {
		this.idCidade = idCidade;
	}
	public String getNomeCidade() {
		return nomeCidade;
	}
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
	public EnumStatus getStatusCidade() {
		return statusCidade;
	}
	public void setStatusCidade(EnumStatus statusCidade) {
		this.statusCidade = statusCidade;
	}
	public POEstado getEstadoCidade() {
		return estadoCidade;
	}
	public void setEstadoCidade(POEstado estadoCidade) {
		this.estadoCidade = estadoCidade;
	}
	
	public JSONObject toJSON() throws JSONException{ 
		JSONObject json = new JSONObject();
		json.put("idCidade", this.idCidade);
		json.put("nomeCidade", this.nomeCidade);
		json.put("statusCidade", this.statusCidade.getStatus());
		json.put("estadoCidade", this.estadoCidade.toJSON());
		return json;
	}
}
