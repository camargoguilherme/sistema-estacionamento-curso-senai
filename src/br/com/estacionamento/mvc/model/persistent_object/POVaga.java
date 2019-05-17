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
@Table(name="TB_VAGA")
public class POVaga {
//	+----------------+-------------------------+------+-----+---------+-------+
//	| Field          | Type                    | Null | Key | Default | Extra |
//	+----------------+-------------------------+------+-----+---------+-------+
//	| TB_VAGA_ID     | int(11)                 | NO   | PRI | NULL    |       |
//	| TB_VAGA_DESC   | varchar(45)             | NO   |     | NULL    |       |
//	| TB_VAGA_STATUS | enum('ativo','inativo') | NO   |     | NULL    |       |
//	+----------------+-------------------------+------+-----+---------+-------+
	@Id
	@GeneratedValue
	@Column(name="TB_VAGA_ID", nullable=false)
	private int idVaga;
	
	@Column(name="TB_VAGA_DESC", nullable=false, length=45)
	private String descVaga;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TB_VAGA_STATUS", nullable=false)
	private EnumStatus statusVaga;

	public int getIdVaga() {
		return idVaga;
	}

	public void setIdVaga(int idVaga) {
		this.idVaga = idVaga;
	}

	public String getDescVaga() {
		return descVaga;
	}

	public void setDescVaga(String descVaga) {
		this.descVaga = descVaga;
	}

	public EnumStatus getStatusVaga() {
		return statusVaga;
	}

	public void setStatusVaga(EnumStatus statusVaga) {
		this.statusVaga = statusVaga;
	}
	
	public JSONObject toJSON() throws JSONException{ 
		JSONObject json = new JSONObject();

		json.put("idVaga", this.idVaga);
		json.put("descVaga", this.descVaga);
		json.put("statusVaga", this.statusVaga.getStatus());
		return json;
	}
}
