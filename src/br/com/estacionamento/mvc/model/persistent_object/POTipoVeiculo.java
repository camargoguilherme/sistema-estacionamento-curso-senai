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
@Table(name = "TB_TIPO_VEICULO")
public class POTipoVeiculo {
	
//	+------------------------+-------------------------+------+-----+---------+-------+
//	| Field                  | Type                    | Null | Key | Default | Extra |
//	+------------------------+-------------------------+------+-----+---------+-------+
//	| TB_TIPO_VEICULO_ID     | int(11)                 | NO   | PRI | NULL    |       |
//	| TB_TIPO_VEICULO_DESC   | varchar(50)             | NO   |     | NULL    |       |
//	| TB_TIPO_VEICULO_STATUS | enum('ativo','inativo') | NO   |     | NULL    |       |
//	+------------------------+-------------------------+------+-----+---------+-------+
	@Id
	@GeneratedValue
	@Column(name = "TB_TIPO_VEICULO_ID", nullable=false)
	private int idTipoVeiculo;
	
	@Column(name = "TB_TIPO_VEICULO_DESC", nullable=false)
	private String descTipoVeiculo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TB_TIPO_VEICULO_STATUS", nullable=false)
	private EnumStatus statusTipoVeiculo;

	public int getIdTipoVeiculo() {
		return idTipoVeiculo;
	}

	public void setIdTipoVeiculo(int idTipoVeiculo) {
		this.idTipoVeiculo = idTipoVeiculo;
	}

	public String getDescTipoVeiculo() {
		return descTipoVeiculo;
	}

	public void setDescTipoVeiculo(String descTipoVeiculo) {
		this.descTipoVeiculo = descTipoVeiculo;
	}

	public EnumStatus getStatusTipoVeiculo() {
		return statusTipoVeiculo;
	}

	public void setStatusTipoVeiculo(EnumStatus statusTipoVeiculo) {
		this.statusTipoVeiculo = statusTipoVeiculo;
	}

	public JSONObject toJSON() throws JSONException{ 
		JSONObject json = new JSONObject();

		json.put("idTipoVeiculo", this.idTipoVeiculo);
		json.put("descTipoVeiculo", this.descTipoVeiculo);
		json.put("statusTipoVeiculo", this.statusTipoVeiculo.getStatus());
		return json;
	}
}
