package br.com.cadastropaciente.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.cadastropaciente.model.Paciente;
import br.com.cadastropaciente.model.dto.PacienteDto;
import br.com.cadastropaciente.model.form.PacienteForm;

/**
 * Mapper para a entidade {@link Paciente}.
 * 
 * @author Felipe Tadeu
 *
 */
@Mapper(componentModel = "spring", uses = EnderecoMapper.class)
public interface PacienteMapper {

	/* Mapeia um formulário para uma entidade. */
	Paciente formToEntity(PacienteForm form);
	
	/* Mapeia entidade para DTO. */
	PacienteDto entityToDto(Paciente entity);
	
	/* Mapeia lista de entidade para lista de DTO. */
	List<PacienteDto> entityToDto(List<Paciente> entityList);

	/* Mapeia um formulário para uma entidade com ID. */
	Paciente formToEntity(Long id, PacienteForm form);

}
