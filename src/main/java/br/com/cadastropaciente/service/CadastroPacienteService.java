package br.com.cadastropaciente.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.cadastropaciente.controller.CadastroPacienteController;
import br.com.cadastropaciente.model.Paciente;
import br.com.cadastropaciente.model.dto.PacienteDto;
import br.com.cadastropaciente.model.form.PacienteForm;
import br.com.cadastropaciente.repository.PacienteRepository;
import br.com.cadastropaciente.service.mapper.PacienteMapper;

/**
 * Service para métodos do controller {@link CadastroPacienteController}.
 * 
 * @author Felipe Tadeu
 *
 */
@Service
@Transactional
public class CadastroPacienteService {

	/* DEPENDÊNCIAS */
	private final PacienteRepository repository;
	private final PacienteMapper mapper;

	/* CONSTRUTOR */
	public CadastroPacienteService(PacienteRepository repository, PacienteMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	// TODO validações e tratamentos
	public PacienteDto cadastrarPaciente(PacienteForm form) {
		Paciente pacienteSalvo = this.repository.save(this.mapper.formToEntity(form));
		return this.mapper.entityToDto(pacienteSalvo);
	}

	// TODO validações e tratamentos
	public PacienteDto detalharDadosPaciente(Long id) {
		Optional<Paciente> pacienteOptional = this.repository.findById(id);
		return this.mapper.entityToDto(pacienteOptional.get());
	}

	// TODO validações e tratamentos
	public List<PacienteDto> listarPacientes() {
		List<Paciente> listaPaciente = this.repository.findAll();
		return this.mapper.entityToDto(listaPaciente);
	}

	// TODO validações e tratamentos
	public PacienteDto atualizarDadosPaciente(Long id, PacienteForm form) {
		Paciente pacienteParaAtualizar = this.mapper.formToEntity(id, form);
		Paciente pacienteAtualizado = this.repository.save(pacienteParaAtualizar);
		return this.mapper.entityToDto(pacienteAtualizado);
	}

	// TODO validações e tratamentos
	public void deletarPaciente(Long id) {
		this.repository.deleteById(id);
	}

}
