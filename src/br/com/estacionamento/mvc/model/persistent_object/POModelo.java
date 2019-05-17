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
@Table(name = "TB_MODELO")
public class POModelo {
//	+--------------------+-------------------------+------+-----+---------+-------+
//	| Field              | Type                    | Null | Key | Default | Extra |
//	+--------------------+-------------------------+------+-----+---------+-------+
//	| TB_MODELO_ID       | int(11)                 | NO   | PRI | NULL    |       |
//	| TB_MODELO_DESC     | varchar(50)             | NO   |     | NULL    |       |
//	| TB_MODELO_STATUS   | enum('ativo','inativo') | NO   |     | NULL    |       |
//	| TB_TIPO_VEICULO_ID | int(11)                 | NO   | MUL | NULL    |       |
//	| TB_MARCA_ID        | int(11)                 | NO   | MUL | NULL    |       |
//	+--------------------+-------------------------+------+-----+---------+-------+
	@Id
	@GeneratedValue
	@Column(name = "TB_MODELO_ID",  nullable=false)
	private int idModelo;
	
	@Column(name="TB_MODELO_DESC", nullable=false, length=50)
	private String descModelo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TB_MODELO_STATUS", nullable=false)
	private EnumStatus statusModelo;
	
	@OneToOne
	@JoinColumn(name="TB_TIPO_VEICULO_ID", nullable=false)
	private POTipoVeiculo tipoVeiculModelo;
	
	@OneToOne
	@JoinColumn(name="TB_MARCA_ID", nullable=false)
	private POMarca marcaModelo;

	public int getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(int idModelo) {
		this.idModelo = idModelo;
	}

	public String getDescModelo() {
		return descModelo;
	}

	public void setDescModelo(String descModelo) {
		this.descModelo = descModelo;
	}

	public EnumStatus getStatusModelo() {
		return statusModelo;
	}

	public void setStatusModelo(EnumStatus statusModelo) {
		this.statusModelo = statusModelo;
	}

	public POTipoVeiculo getTipoVeiculModelo() {
		return tipoVeiculModelo;
	}

	public void setTipoVeiculModelo(POTipoVeiculo tipoVeiculModelo) {
		this.tipoVeiculModelo = tipoVeiculModelo;
	}

	public POMarca getMarcaModelo() {
		return marcaModelo;
	}

	public void setMarcaModelo(POMarca marcaModelo) {
		this.marcaModelo = marcaModelo;
	}
	
	public JSONObject toJSON() throws JSONException{ 
		JSONObject json = new JSONObject();

		json.put("idModelo", this.idModelo);
		json.put("descModelo", this.descModelo);
		json.put("tipoVeiculModelo", this.tipoVeiculModelo.toJSON());
		json.put("statusModelo", this.statusModelo.getStatus());
		json.put("marcaModelo", this.marcaModelo.toJSON());
		return json;
	}
}
