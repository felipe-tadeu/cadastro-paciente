package br.com.cadastropaciente.config.excepiton.custom;

/**
 * Exceção para quando houver alguma falha de requisição ao banco de dados.
 * 
 * @author Felipe Tadeu
 *
 */
public class FalhaRequisicaoBancoDadosException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor padrão.
	 * 
	 * @param message mensagem customizada
	 */
	public FalhaRequisicaoBancoDadosException(String message) {
		super(message);
	}

}
