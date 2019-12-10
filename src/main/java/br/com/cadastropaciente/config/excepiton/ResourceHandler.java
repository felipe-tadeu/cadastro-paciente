package br.com.cadastropaciente.config.excepiton;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.cadastropaciente.config.excepiton.custom.CampoFormularioInvalidoException;
import br.com.cadastropaciente.config.excepiton.custom.FalhaRequisicaoBancoDadosException;
import br.com.cadastropaciente.config.excepiton.custom.PacienteJaCadastradoException;

/**
 * Classe para manipulação de exceções customizadas.
 * 
 * @author Felipe Tadeu
 *
 */
@RestControllerAdvice
public class ResourceHandler {
	
	/**
	 * Manipular erro BAD_REQUEST(400).
	 * 
	 * @param e {@link Exception}
	 * @return {@link StandardError}
	 */
	@ExceptionHandler({CampoFormularioInvalidoException.class, PacienteJaCadastradoException.class})
	ResponseEntity<StandardError> badRequest(Exception e) {
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST, e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	/**
	 * Manipular erro INTERNAL_SERVER_ERROR(500).
	 * 
	 * @param e {@link Exception}
	 * @return {@link StandardError}
	 */
	@ExceptionHandler({FalhaRequisicaoBancoDadosException.class})
	ResponseEntity<StandardError> internalServerError(Exception e) {
		StandardError err = new StandardError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}

}
