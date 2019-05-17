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
@Table(name="TB_TABELA_PRECO")
public class POTabelaPreco {
//	+---------------------------+-------------------------+------+-----+---------+-------+
//	| Field                     | Type                    | Null | Key | Default | Extra |
//	+---------------------------+-------------------------+------+-----+---------+-------+
//	| TB_TABELA_PRECO_ID        | int(11)                 | NO   | PRI | NULL    |       |
//	| TB_TABELA_PRECO_VALOR     | double                  | NO   |     | NULL    |       |
//	| TB_TABELA_PRECO_STATUS    | enum('ativo','inativo') | NO   |     | NULL    |       |
//	| TB_TABELA_PRECO_TEMPO_MIN | int(11)                 | NO   |     | NULL    |       |
//	| TB_TABELA_PRECO_TEMPO_MAX | int(11)                 | NO   |     | NULL    |       |
//	+---------------------------+-------------------------+------+-----+---------+-------+
	@Id
	@GeneratedValue
	@Column(name="TB_TABELA_PRECO_ID", nullable=false)
	private int idPreco;
	
	@Column(name="TB_TABELA_PRECO_VALOR", nullable=false)
	private Double valorPreco;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TB_TABELA_PRECO_STATUS", nullable=false)
	private EnumStatus statusPreco;
	
	@Column(name="TB_TABELA_PRECO_TEMPO_MIN", nullable=false)
	private int tempoMinPreco;
	
	@Column(name="TB_TABELA_PRECO_TEMPO_MAX", nullable=false)
	private int tempoMaxPreco;

	public int getIdPreco() {
		return idPreco;
	}

	public void setIdPreco(int idPreco) {
		this.idPreco = idPreco;
	}

	public Double getValorPreco() {
		return valorPreco;
	}

	public void setValorPreco(Double valorPreco) {
		this.valorPreco = valorPreco;
	}

	public EnumStatus getStatusPreco() {
		return statusPreco;
	}

	public void setStatusPreco(EnumStatus statusPreco) {
		this.statusPreco = statusPreco;
	}

	public int getTempoMinPreco() {
		return tempoMinPreco;
	}

	public void setTempoMinPreco(int tempoMinPreco) {
		this.tempoMinPreco = tempoMinPreco;
	}

	public int getTempoMaxPreco() {
		return tempoMaxPreco;
	}

	public void setTempoMaxPreco(int tempoMaxPreco) {
		this.tempoMaxPreco = tempoMaxPreco;
	}
	
	public JSONObject toJSON() throws JSONException{ 
		JSONObject json = new JSONObject();

		json.put("idPreco", this.idPreco);
		json.put("valorPreco", this.valorPreco);
		json.put("statusPreco", this.statusPreco.getStatus());
		json.put("tempoMinPreco", this.tempoMinPreco);
		json.put("tempoMaxPreco", this.tempoMaxPreco);
		return json;
	}
}
