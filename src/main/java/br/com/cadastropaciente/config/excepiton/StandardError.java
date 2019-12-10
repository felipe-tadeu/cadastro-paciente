package br.com.cadastropaciente.config.excepiton;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe para erro padrão.
 * 
 * @author Felipe Tadeu
 *
 */
@Getter
@Setter
public class StandardError {
	
	private int status;
	private String message;
	private LocalDateTime timeStamp = LocalDateTime.now();
	
	/**
	 * Construtor padrão da classe.
	 * 
	 * @param httpStatus {@link HttpStatus} do erro
	 * @param message mensagem customizada do erro
	 */
	public StandardError(HttpStatus httpStatus, String message) {
		this.status = httpStatus.value();
		this.message = message;
	}

}
