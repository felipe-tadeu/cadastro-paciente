package br.com.cadastropaciente.model.dto;

import br.com.cadastropaciente.model.Endereco;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para entidade {@link Endereco}.
 * 
 * @author Felipe Tadeu
 *
 */
@Getter
@Setter
public class EnderecoDto {

	private Long id;

	private Long cep;

	private String endereco;

	private Long numero;

	private String complemento;
	
}
