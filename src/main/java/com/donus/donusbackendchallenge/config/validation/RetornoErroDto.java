package com.donus.donusbackendchallenge.config.validation;

public class RetornoErroDto {
	
	private String campo;
	private String erro;
	
	public RetornoErroDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}
