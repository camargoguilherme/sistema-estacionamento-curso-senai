package br.com.estacionamento.mvc.model.persistent_object.enums;

public enum EnumTipoTelefone {
	COMERCIAL("comercial"),
	RESIDENCIAL("residencial"),
	CELULAR("celular");
	
	private String tipo;
	
	private EnumTipoTelefone(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
}
