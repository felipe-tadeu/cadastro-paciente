package br.com.cadastropaciente.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.cadastropaciente.config.excepiton.custom.FalhaRequisicaoBancoDadosException;
import br.com.cadastropaciente.controller.CadastroPacienteController;
import br.com.cadastropaciente.model.Paciente;
import br.com.cadastropaciente.model.dto.PacienteDto;
import br.com.cadastropaciente.model.form.PacienteForm;
import br.com.cadastropaciente.repository.PacienteRepository;
import br.com.cadastropaciente.service.mapper.PacienteMapper;
import br.com.cadastropaciente.service.utils.ValidacaoService;

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
	private final ValidacaoService validacaoService;

	/* CONSTRUTOR */
	public CadastroPacienteService(PacienteRepository repository, PacienteMapper mapper,
			ValidacaoService validacaoService) {
		this.repository = repository;
		this.mapper = mapper;
		this.validacaoService = validacaoService;
	}

	/**
	 * Função para cadastrar um novo paciente.
	 * 
	 * @param form {@link PacienteForm} com dados do paciente a ser cadastrado
	 * @return {@link PacienteDto} com dados do novo paciente cadastrado
	 */
	public PacienteDto cadastrarPaciente(PacienteForm form) {

		this.validacaoService.validarFormularioCadastroPaciente(form, repository);

		Paciente pacienteSalvo;
		try {
			pacienteSalvo = this.repository.save(this.mapper.formToEntity(form));
		} catch (Exception e) {
			throw new FalhaRequisicaoBancoDadosException("Falha ao salvar paciente no banco de dados.");
		}

		return this.mapper.entityToDto(pacienteSalvo);

	}

	/**
	 * Obtém o menor número de prontuário disponível.
	 * 
	 * @return número de prontuário
	 */
	public Long obterNumeroPronturario() {
		if (!this.repository.findByNumeroProntuario(1L).isPresent()) {
			return 1L;
		}
		return this.repository.obterNumeroProntuario().get();
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
