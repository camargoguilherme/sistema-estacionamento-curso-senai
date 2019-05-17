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

import org.hibernate.annotations.Type;

import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

@Entity
@Table(name="TB_MENSALISTA_VEICULO")
public class POMensalistaVeiculo {

//	+------------------------------------+-------------------------+------+-----+---------+-------+
//	| Field                              | Type                    | Null | Key | Default | Extra |
//	+------------------------------------+-------------------------+------+-----+---------+-------+
//	| TB_MENSALISTA_VEICULO_ID           | int(11)                 | NO   | PRI | NULL    |       |
//	| TB_MENSALISTA_VEICULO_PROPRIETARIO | tinyint(1)              | NO   |     | NULL    |       |
//	| TB_MENSALISTA_ID                   | int(11)                 | NO   | MUL | NULL    |       |
//	| TB_VEICULO_ID                      | int(11)                 | NO   | MUL | NULL    |       |
//	| TB_MENSALISTA_VEICULO_STATUS       | enum('ativo','inativo') | NO   |     | NULL    |       |
//	+------------------------------------+-------------------------+------+-----+---------+-------+
	@Id
	@GeneratedValue
	@Column(name="TB_MENSALISTA_VEICULO_ID", nullable=false)
	private int idMensalistaVeiculo;
	
	@Column(name="TB_MENSALISTA_VEICULO_PROPRIETARIO", nullable=false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean proprietarioMensalistaVeiculo;
	
	@OneToOne
	@JoinColumn(name="TB_MENSALISTA_ID", nullable=false)
	private POMensalista mensalista;
	
	@OneToOne
	@JoinColumn(name="TB_VEICULO_ID", nullable=false)
	private POVeiculo veiculo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TB_MENSALISTA_VEICULO_STATUS", nullable=false)
	private EnumStatus statusMensalistaVeiculo;

	public int getIdMensalistaVeiculo() {
		return idMensalistaVeiculo;
	}

	public void setIdMensalistaVeiculo(int idMensalistaVeiculo) {
		this.idMensalistaVeiculo = idMensalistaVeiculo;
	}

	public boolean isProprietarioMensalistaVeiculo() {
		return proprietarioMensalistaVeiculo;
	}

	public void setProprietarioMensalistaVeiculo(boolean proprietarioMensalistaVeiculo) {
		this.proprietarioMensalistaVeiculo = proprietarioMensalistaVeiculo;
	}

	public POMensalista getMensalista() {
		return mensalista;
	}

	public void setMensalista(POMensalista mensalistaMensalistaVeiculo) {
		this.mensalista = mensalistaMensalistaVeiculo;
	}

	public POVeiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(POVeiculo veiculoMensalistaVeiculo) {
		this.veiculo = veiculoMensalistaVeiculo;
	}

	public EnumStatus getStatusMensalistaVeiculo() {
		return statusMensalistaVeiculo;
	}

	public void setStatusMensalistaVeiculo(EnumStatus statusMensalistaVeiculo) {
		this.statusMensalistaVeiculo = statusMensalistaVeiculo;
	}	
}
