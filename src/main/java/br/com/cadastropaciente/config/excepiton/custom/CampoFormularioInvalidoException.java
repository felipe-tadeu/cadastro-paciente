package br.com.cadastropaciente.config.excepiton.custom;

/**
 * Exceção para quando algum campo de formulário estiver inválido.
 * 
 * @author Felipe Tadeu
 *
 */
public class CampoFormularioInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor padrão.
	 * 
	 * @param message mensagem customizada
	 */
	public CampoFormularioInvalidoException(String message) {
		super(message);
	}

}
