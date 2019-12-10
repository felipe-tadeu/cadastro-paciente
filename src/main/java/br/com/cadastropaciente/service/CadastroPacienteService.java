package br.com.cadastropaciente.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.cadastropaciente.config.excepiton.custom.CampoFormularioInvalidoException;
import br.com.cadastropaciente.config.excepiton.custom.FalhaRequisicaoBancoDadosException;
import br.com.cadastropaciente.config.excepiton.custom.PacienteJaCadastradoException;
import br.com.cadastropaciente.controller.CadastroPacienteController;
import br.com.cadastropaciente.model.Paciente;
import br.com.cadastropaciente.model.dto.PacienteDto;
import br.com.cadastropaciente.model.form.PacienteForm;
import br.com.cadastropaciente.repository.PacienteRepository;
import br.com.cadastropaciente.service.mapper.PacienteMapper;
import br.com.caelum.stella.validation.CPFValidator;

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

	/**
	 * Função para cadastrar um novo paciente.
	 * 
	 * @param form {@link PacienteForm} com dados do paciente a ser cadastrado
	 * @return {@link PacienteDto} com dados do novo paciente cadastrado
	 */
	public PacienteDto cadastrarPaciente(PacienteForm form) {

		validarFormulario(form);

		Paciente pacienteSalvo;
		try {
			pacienteSalvo = this.repository.save(this.mapper.formToEntity(form));
		} catch (Exception e) {
			throw new FalhaRequisicaoBancoDadosException("Falha ao salvar paciente no banco de dados.");
		}

		return this.mapper.entityToDto(pacienteSalvo);

	}

	/**
	 * Função para validação dos dados do formulário de cadastro de paciente.
	 * 
	 * @param form {@link PacienteForm} com dados para validação
	 * 
	 * @throws CampoFormularioInvalidoException quando houver algum campo do
	 *                                          formulário inválido
	 * @throws PacienteJaCadastradoException    quando já houver algum paciente com
	 *                                          dado do formulário cadastrado
	 */
	private void validarFormulario(PacienteForm form) {

		// validação do nome
		if (form.getNome() == null || form.getNome().isEmpty() || !form.getNome().matches(
				"^[A-Za-z]+([\\']{1}[A-Za-z]+([ ]{1})?)?(([A-Za-z]+[\\']{1})?([A-Za-z]*)?([A-Za-z]+([ ]{1})?))*$")) {
			throw new CampoFormularioInvalidoException("Nome inválido.");
		}

		// validação do CPF
		CPFValidator cpfValidator = new CPFValidator();
		try {
			cpfValidator.assertValid(form.getCpf());
		} catch (Exception e) {
			throw new CampoFormularioInvalidoException("CPF inválido.");
		}
		if (this.repository.findByCpf(form.getCpf()).isPresent()) {
			throw new PacienteJaCadastradoException("CPF já cadastrado.");
		}

		// validação do nome social
		if (form.isSeDesejaUsarNomeSocial()) {
			if (form.getNomeSocial() == null || form.getNomeSocial().isEmpty() || !form.getNomeSocial().matches(
					"^[A-Za-z]+([\\']{1}[A-Za-z]+([ ]{1})?)?(([A-Za-z]+[\\']{1})?([A-Za-z]*)?([A-Za-z]+([ ]{1})?))*$")) {
				throw new CampoFormularioInvalidoException("Nome social inválido.");
			}
		} else {
			form.setNomeSocial(null);
		}

		// validação número prontuário
		if (form.getNumeroProntuario() == null) {
			throw new CampoFormularioInvalidoException("Número do prontuário inválido.");
		}
		if (this.repository.findByNumeroProntuario(form.getNumeroProntuario()).isPresent()) {
			throw new PacienteJaCadastradoException("Número do prontuário já cadastrado.");
		}

		// validação data de nascimento
		if (form.getDataNascimento().isAfter(LocalDate.now())) {
			throw new CampoFormularioInvalidoException("Data de nascimento inválida.");
		}

		// validação sexo
		if (form.getSexo() == null) {
			throw new CampoFormularioInvalidoException("Sexo inválido.");
		}
		
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
