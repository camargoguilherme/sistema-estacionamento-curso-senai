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

import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

@Entity
@Table(name="TB_USUARIO")
public class POUsuario {
	
//	+-------------------+-------------------------+------+-----+---------+-------+
//	| Field             | Type                    | Null | Key | Default | Extra |
//	+-------------------+-------------------------+------+-----+---------+-------+
//	| TB_USUARIO_ID     | int(11)                 | NO   | PRI | NULL    |       |
//	| TB_USUARIO_NOME   | varchar(10)             | NO   |     | NULL    |       |
//	| TB_USUARIO_SENHA  | varchar(10)             | NO   |     | NULL    |       |
//	| TB_USUARIO_STATUS | enum('ativo','inativo') | NO   |     | NULL    |       |
//	+-------------------+-------------------------+------+-----+---------+-------+
	
	@Id
	@GeneratedValue
	@Column(name = "TB_USUARIO_ID", nullable=false)
	private int idUsuario;
	
	@Column(name = "TB_USUARIO_NOME", nullable=false)
	private String nomeUsuario;
	
	@Column(name = "TB_USUARIO_SENHA", nullable=false)
	private String senhaUsuario;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TB_USUARIO_STATUS", nullable=false)
	private EnumStatus statusUsuario;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public EnumStatus getStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(EnumStatus statusUsuario) {
		this.statusUsuario = statusUsuario;
	}
	
}
