package br.com.cadastropaciente.config.excepiton.custom;

/**
 * Exceção para quando paciente com CPF ou número do prontuário estiver sido
 * cadastrado.
 * 
 * @author Felipe Tadeu
 *
 */
public class PacienteJaCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor padrão.
	 * 
	 * @param message mensagem customizada
	 */
	public PacienteJaCadastradoException(String message) {
		super(message);
	}

}
