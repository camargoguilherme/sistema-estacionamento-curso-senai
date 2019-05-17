package br.com.estacionamento.mvc.model.persistent_object;

import java.time.LocalDateTime;

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
@Table(name="TB_ESTADIA")
public class POEstadia {
//	+--------------------+-------------------------+------+-----+-------------------+-----------------------------+
//	| Field              | Type                    | Null | Key | Default           | Extra                       |
//	+--------------------+-------------------------+------+-----+-------------------+-----------------------------+
//	| TB_ESTADIA_ID      | int(11)                 | NO   | PRI | NULL              |                             |
//	| TB_ESTADIA_INICIO  | timestamp               | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
//	| TB_ESTADIA_TERMINO | timestamp               | YES  |     | NULL              |                             |
//	| TB_ESTADIA_PLACA   | varchar(8)              | NO   |     | NULL              |                             |
//	| TB_ESTADIA_STATUS  | enum('ativo','inativo') | NO   |     | NULL              |                             |
//	| TB_TABELA_PRECO_ID | int(11)                 | NO   | MUL | NULL              |                             |
//	| TB_VAGA_ID         | int(11)                 | NO   | MUL | NULL              |                             |
//	| TB_VEICULO_ID      | int(11)                 | YES  | MUL | NULL              |                             |
//	+--------------------+-------------------------+------+-----+-------------------+-----------------------------+
	@Id
	@GeneratedValue
	@Column(name="TB_ESTADIA_ID", nullable=false)
	private int idEstadia;
	
	@Column(name="TB_ESTADIA_INICIO", nullable=false)
	private LocalDateTime inicioEstadia;
	
	@Column(name="TB_ESTADIA_TERMINO", nullable=true)
	private LocalDateTime terminoEstadia;
	
	@Column(name="TB_ESTADIA_PLACA", nullable=false, length=8)
	private String placaEstadia;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TB_ESTADIA_STATUS", nullable=false)
	private EnumStatus statusEstadia;
	
	@OneToOne
	@JoinColumn(name="TB_TABELA_PRECO_ID", nullable=false)
	private POTabelaPreco precoEstadia;
	
	@OneToOne
	@JoinColumn(name="TB_VAGA_ID", nullable=false)
	private POVaga vagaEstadia;
	
	@OneToOne
	@JoinColumn(name="TB_VEICULO_ID", nullable=true)
	private POVeiculo veiculoEstadia;

	public int getIdEstadia() {
		return idEstadia;
	}

	public void setIdEstadia(int idEstadia) {
		this.idEstadia = idEstadia;
	}

	public LocalDateTime getInicioEstadia() {
		return inicioEstadia;
	}

	public void setInicioEstadia(LocalDateTime inicioEstadia) {
		this.inicioEstadia = inicioEstadia;
	}

	public LocalDateTime getTerminoEstadia() {
		return terminoEstadia;
	}

	public void setTerminoEstadia(LocalDateTime terminoEstadia) {
		this.terminoEstadia = terminoEstadia;
	}

	public String getPlacaEstadia() {
		return placaEstadia;
	}

	public void setPlacaEstadia(String placaEstadia) {
		this.placaEstadia = placaEstadia;
	}

	public EnumStatus getStatusEstadia() {
		return statusEstadia;
	}

	public void setStatusEstadia(EnumStatus statusEstadia) {
		this.statusEstadia = statusEstadia;
	}

	public POTabelaPreco getPrecoEstadia() {
		return precoEstadia;
	}

	public void setPrecoEstadia(POTabelaPreco precoEstadia) {
		this.precoEstadia = precoEstadia;
	}

	public POVaga getVagaEstadia() {
		return vagaEstadia;
	}

	public void setVagaEstadia(POVaga vagaEstadia) {
		this.vagaEstadia = vagaEstadia;
	}

	public POVeiculo getVeiculoEstadia() {
		return veiculoEstadia;
	}

	public void setVeiculoEstadia(POVeiculo veiculoEstadia) {
		this.veiculoEstadia = veiculoEstadia;
	}
	public JSONObject toJSON() throws JSONException{ 
		JSONObject json = new JSONObject();

		json.put("idEstadia", this.idEstadia);
		json.put("inicioEstadia", this.inicioEstadia);
		json.put("terminoEstadia", this.terminoEstadia);
		json.put("precoEstadia", this.precoEstadia.toJSON());
		json.put("vagaEstadia", this.vagaEstadia.toJSON());
		json.put("statusEstadia", this.statusEstadia.getStatus());
		json.put("veiculoEstadia", this.veiculoEstadia.toJSON());
		return json;
	}
}
