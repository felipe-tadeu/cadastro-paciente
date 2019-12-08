package br.com.cadastropaciente.service.mapper;

import org.mapstruct.Mapper;

import br.com.cadastropaciente.model.Endereco;
import br.com.cadastropaciente.model.dto.EnderecoDto;
import br.com.cadastropaciente.model.form.EnderecoForm;

/**
 * Mapper para a entidade {@link Endereco}.
 * 
 * @author Felipe Tadeu
 *
 */
@Mapper(componentModel = "spring")
public interface EnderecoMapper {
	
	/* Mapeia formulário para entidade. */
	Endereco formToEntity(EnderecoForm form);
	
	/* Mapeia entidade para DTO. */
	EnderecoDto entityToDto(Endereco entity);

}
