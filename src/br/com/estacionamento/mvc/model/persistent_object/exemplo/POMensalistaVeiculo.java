package br.com.estacionamento.mvc.model.persistent_object.exemplo;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import br.com.estacionamento.mvc.model.persistent_object.POMensalista;
import br.com.estacionamento.mvc.model.persistent_object.POVeiculo;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;


@Entity
@Table(name = "TB_MENSALISTA_VEICULO")
public class POMensalistaVeiculo {
//	+------------------------------------+-------------------------+------+-----+---------+-------+
//	| Field                              | Type                    | Null | Key | Default | Extra |
//	+------------------------------------+-------------------------+------+-----+---------+-------+
//	| TB_MENSALISTA_VEICULO_PROPRIETARIO | tinyint(1)              | NO   |     | NULL    |       |
//	| TB_MENSALISTA_ID                   | int(11)                 | NO   | MUL | NULL    |       |
//	| TB_VEICULO_ID                      | int(11)                 | NO   | MUL | NULL    |       |
//	| TB_MENSALISTA_VEICULO_STATUS       | enum('ativo','inativo') | NO   |     | NULL    |       |
//	+------------------------------------+-------------------------+------+-----+---------+-------+
	
	@EmbeddedId
	private EmbeddedMensalistaVeiculoPK idMensalistaVeiculo;
	
	@Column(name="TB_MENSALISTA_VEICULO_PROPRIETARIO", nullable=false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean proprietarioMensalistaVeiculo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TB_MENSALISTA_VEICULO_STATUS", nullable=false)
	private EnumStatus statusMensalistaVeiculo;

	public EmbeddedMensalistaVeiculoPK getIdMensalistaVeiculo() {
		return idMensalistaVeiculo;
	}

	public void setIdMensalistaVeiculo(EmbeddedMensalistaVeiculoPK idMensalistaVeiculo) {
		this.idMensalistaVeiculo = idMensalistaVeiculo;
	}

	public boolean isProprietarioMensalistaVeiculo() {
		return proprietarioMensalistaVeiculo;
	}

	public void setProprietarioMensalistaVeiculo(boolean proprietarioMensalistaVeiculo) {
		this.proprietarioMensalistaVeiculo = proprietarioMensalistaVeiculo;
	}

	public EnumStatus getStatusMensalistaVeiculo() {
		return statusMensalistaVeiculo;
	}

	public void setStatusMensalistaVeiculo(EnumStatus statusMensalistaVeiculo) {
		this.statusMensalistaVeiculo = statusMensalistaVeiculo;
	}
	
	
}
