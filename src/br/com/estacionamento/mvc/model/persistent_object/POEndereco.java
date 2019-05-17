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
@Table(name = "TB_ENDERECO")
public class POEndereco {
//	+------------------------+-------------------------+------+-----+---------+-------+
//	| Field                  | Type                    | Null | Key | Default | Extra |
//	+------------------------+-------------------------+------+-----+---------+-------+
//	| TB_ENDERECO_ID         | int(11)                 | NO   | PRI | NULL    |       |
//	| TB_ENDERECO_CEP        | varchar(8)              | NO   |     | NULL    |       |
//	| TB_ENDERECO_LOGRADOURO | varchar(100)            | NO   |     | NULL    |       |
//	| TB_ENDERECO_NUM        | varchar(10)             | NO   |     | NULL    |       |
//	| TB_ENDERECO_COMP       | varchar(100)            | YES  |     | NULL    |       |
//	| TB_ENDERECO_STATUS     | enum('ativo','inativo') | NO   |     | NULL    |       |
//	| TB_CIDADE_ID           | int(11)                 | NO   | MUL | NULL    |       |
//	| TB_MENSALISTA_ID       | int(11)                 | NO   | MUL | NULL    |       |
//	+------------------------+-------------------------+------+-----+---------+-------+
	@Id
	@GeneratedValue
	@Column(name="TB_ENDERECO_ID", nullable=false)
	private int idEndereco;
	
	@Column(name="TB_ENDERECO_CEP", nullable=false, length=8)
	private String cepEndereco;
	
	@Column(name="TB_ENDERECO_LOGRADOURO", nullable=false, length=100)
	private String logradouroEndereco;
	
	@Column(name="TB_ENDERECO_NUM", nullable=false, length=10)
	private String numEndereco;
	
	@Column(name="TB_ENDERECO_COMP", nullable=false, length=100)
	private String compEndereco;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TB_ENDERECO_STATUS", nullable=false)
	private EnumStatus statusEndereco;
	
	@OneToOne
	@JoinColumn(name = "TB_CIDADE_ID", nullable = false)
	private POCidade cidadeEndereco;
	
	@OneToOne
	@JoinColumn(name = "TB_MENSALISTA_ID", nullable = false)
	private POMensalista mensalistaEndereco;

	public int getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getCepEndereco() {
		return cepEndereco;
	}

	public void setCepEndereco(String cepEndereco) {
		this.cepEndereco = cepEndereco;
	}

	public String getLogradouroEndereco() {
		return logradouroEndereco;
	}

	public void setLogradouroEndereco(String logradouroEndereco) {
		this.logradouroEndereco = logradouroEndereco;
	}

	public String getNumEndereco() {
		return numEndereco;
	}

	public void setNumEndereco(String numEndereco) {
		this.numEndereco = numEndereco;
	}

	public String getCompEndereco() {
		return compEndereco;
	}

	public void setCompEndereco(String compEndereco) {
		this.compEndereco = compEndereco;
	}

	public EnumStatus getStatusEndereco() {
		return statusEndereco;
	}

	public void setStatusEndereco(EnumStatus statusEndereco) {
		this.statusEndereco = statusEndereco;
	}

	public POCidade getCidadeEndereco() {
		return cidadeEndereco;
	}

	public void setCidadeEndereco(POCidade cidadeEndereco) {
		this.cidadeEndereco = cidadeEndereco;
	}

	public POMensalista getMensalistaEndereco() {
		return mensalistaEndereco;
	}

	public void setMensalistaEndereco(POMensalista mensalistaEndereco) {
		this.mensalistaEndereco = mensalistaEndereco;
	}	
	
	public JSONObject toJSON() throws JSONException{ 
		JSONObject json = new JSONObject();

		json.put("idEndereco", this.idEndereco);
		json.put("cepEndereco", this.cepEndereco);
		json.put("logradouroEndereco", this.logradouroEndereco);
		json.put("numEndereco", this.numEndereco);
		json.put("compEndereco", this.compEndereco);
		json.put("statusEndereco", this.statusEndereco.getStatus());
		json.put("cidadeEndereco", this.cidadeEndereco.toJSON());
		json.put("mensalistaEndereco", this.mensalistaEndereco.toJSON());
		return json;
	}

}
