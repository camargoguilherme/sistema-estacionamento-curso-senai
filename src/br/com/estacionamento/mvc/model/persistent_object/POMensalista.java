package br.com.estacionamento.mvc.model.persistent_object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

@Entity
@Table(name = "TB_MENSALISTA")
public class POMensalista {
//	+----------------------+-------------------------+------+-----+---------+----------------+
//	| Field                | Type                    | Null | Key | Default | Extra          |
//	+----------------------+-------------------------+------+-----+---------+----------------+
//	| TB_MENSALISTA_ID     | int(11)                 | NO   | PRI | NULL    | auto_increment |
//	| TB_MENSALISTA_NOME   | varchar(100)            | NO   |     | NULL    |                |
//	| TB_MENSALISTA_CPF    | varchar(11)             | NO   |     | NULL    |                |
//	| TB_MENSALISTA_STATUS | enum('ativo','inativo') | NO   |     | NULL    |                |
//	+----------------------+-------------------------+------+-----+---------+----------------+
	
	@Id
	@GeneratedValue
	@Column(name = "TB_MENSALISTA_ID", nullable = false)
	private int idMensalista;

	@Column(name="TB_MENSALISTA_NOME", nullable=false, length=100)
	private String nomeMensalista;

	@Column(name = "TB_MENSALISTA_CPF", nullable = false, length=11)
	private String cpfMensalista;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TB_MENSALISTA_STATUS", nullable = false)
	private EnumStatus statusMensalista;

	public int getIdMensalista() {
		return idMensalista;
	}

	public void setIdMensalista(int idMensalista) {
		this.idMensalista = idMensalista;
	}

	public String getNomeMensalista() {
		return nomeMensalista;
	}

	public void setNomeMensalista(String nomeMensalista) {
		this.nomeMensalista = nomeMensalista;
	}

	public String getCpfMensalista() {
		return cpfMensalista;
	}

	public void setCpfMensalista(String cpfMensalista) {
		this.cpfMensalista = cpfMensalista;
	}

	public EnumStatus getStatusMensalista() {
		return statusMensalista;
	}

	public void setStatusMensalista(EnumStatus statusMensalista) {
		this.statusMensalista = statusMensalista;
	}
	
	public JSONObject toJSON() throws JSONException{ 
		JSONObject json = new JSONObject();

		json.put("idMensalista", this.idMensalista);
		json.put("nomeMensalista", this.nomeMensalista);
		json.put("cpfMensalista", this.cpfMensalista);
		json.put("statusMensalista", this.statusMensalista.getStatus());
		return json;
	}

}
