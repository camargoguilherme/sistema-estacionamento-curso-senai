package br.com.estacionamento.mvc.model.persistent_object.enums;

public enum EnumStatus {
	ATIVO ("ativo"),
	INATIVO ("inativo");
	
	private String status;

	
	EnumStatus(String status) {
		this.status = status;
	}


	public String getStatus() {
		return status;
	}
	
}
