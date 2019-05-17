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

import com.mysql.cj.conf.ConnectionUrl.Type;

import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumTipoTelefone;

@Entity
@Table(name = "TB_TELEFONE")
public class POTelefone {
//	+----------------------+-------------------------------------------+------+-----+---------+----------------+
//	| Field                | Type                                      | Null | Key | Default | Extra          |
//	+----------------------+-------------------------------------------+------+-----+---------+----------------+
//	| TB_TELEFONE_ID       | int(11)                                   | NO   | PRI | NULL    | auto_increment |
//	| TB_TELEFONE_NUM      | varchar(11)                               | NO   |     | NULL    |                |
//	| TB_TELEFONE_TIPO_TEL | enum('residencial','comercial','celular') | NO   |     | NULL    |                |
//	| TB_TELEFONE_STATUS   | enum('ativo','inativo')                   | NO   |     | NULL    |                |
//	| TB_MENSALISTA_ID     | int(11)                                   | NO   | MUL | NULL    |                |
//	+----------------------+-------------------------------------------+------+-----+---------+----------------+
	@Id
	@GeneratedValue
	@Column(name = "TB_TELEFONE_ID", nullable=false)
	private int idTelefone;
	
	@Column(name = "TB_TELEFONE_NUM", nullable=false, length=11)
	private String numTelefone;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TB_TELEFONE_TIPO_TEL", nullable=false)
	private EnumTipoTelefone tipoTelefone;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TB_TELEFONE_STATUS", nullable=false)
	private EnumStatus statusTelefone;
	
	@OneToOne
	@JoinColumn(name = "TB_MENSALISTA_ID", nullable=false)
	private POMensalista mensalistaTelefone;

	public int getIdTelefone() {
		return idTelefone;
	}

	public void setIdTelefone(int idTelefone) {
		this.idTelefone = idTelefone;
	}

	public String getNumTelefone() {
		return numTelefone;
	}

	public void setNumTelefone(String numTelefone) {
		this.numTelefone = numTelefone;
	}

	public EnumTipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(EnumTipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public EnumStatus getStatusTelefone() {
		return statusTelefone;
	}

	public void setStatusTelefone(EnumStatus statusTelefone) {
		this.statusTelefone = statusTelefone;
	}

	public POMensalista getMensalistaTelefone() {
		return mensalistaTelefone;
	}

	public void setMensalistaTelefone(POMensalista mensalistaTelefone) {
		this.mensalistaTelefone = mensalistaTelefone;
	}
	
	public JSONObject toJSON() throws JSONException{ 
		JSONObject json = new JSONObject();
		json.put("idTelefone", this.idTelefone);
		json.put("numTelefone", this.numTelefone);
		json.put("tipoTelefone", this.tipoTelefone.getTipo());
		json.put("statusTelefone", this.statusTelefone.getStatus());
		json.put("mensalistaTelefone", this.mensalistaTelefone.toJSON());
		return json;
	}
}
