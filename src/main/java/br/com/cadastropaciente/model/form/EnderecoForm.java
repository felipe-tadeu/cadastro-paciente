package br.com.cadastropaciente.model.form;

import lombok.Getter;
import lombok.Setter;

/**
 * Formulário para Endereço.
 * 
 * @author Felipe Tadeu
 *
 */
@Getter
@Setter
public class EnderecoForm {
	
	private Long cep;
	
    private String endereco;
    
    private Long numero;
    
    private String complemento;

}
