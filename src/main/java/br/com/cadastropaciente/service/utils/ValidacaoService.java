package br.com.cadastropaciente.service.utils;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.com.cadastropaciente.config.excepiton.custom.CampoFormularioInvalidoException;
import br.com.cadastropaciente.config.excepiton.custom.PacienteJaCadastradoException;
import br.com.cadastropaciente.model.form.PacienteForm;
import br.com.cadastropaciente.repository.PacienteRepository;
import br.com.caelum.stella.validation.CPFValidator;

/**
 * Service para funções de validação.
 * 
 * @author Felipe Tadeu
 *
 */
@Service
public class ValidacaoService {

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
	public void validarFormularioCadastroPaciente(PacienteForm form, PacienteRepository repository) {

		// validação do nome
		this.validacaoNome(form.getNome(), "Nome inválido.");

		// validação do CPF
		CPFValidator cpfValidator = new CPFValidator();
		try {
			cpfValidator.assertValid(form.getCpf());
		} catch (Exception e) {
			throw new CampoFormularioInvalidoException("CPF inválido.");
		}
		if (repository.findByCpf(form.getCpf()).isPresent()) {
			throw new PacienteJaCadastradoException("CPF já cadastrado.");
		}

		// validação do nome social
		if (form.isSeDesejaUsarNomeSocial()) {
			this.validacaoNome(form.getNomeSocial(), "Nome social inválido.");
		} else {
			form.setNomeSocial(null);
		}

		// validação número prontuário
		if (form.getNumeroProntuario() == null) {
			throw new CampoFormularioInvalidoException("Número do prontuário inválido.");
		}
		if (repository.findByNumeroProntuario(form.getNumeroProntuario()).isPresent()) {
			throw new PacienteJaCadastradoException("Número do prontuário já cadastrado.");
		}

		// validação data de nascimento
		if (form.getDataNascimento() == null || form.getDataNascimento().isAfter(LocalDate.now())) {
			throw new CampoFormularioInvalidoException("Data de nascimento inválida.");
		}

		// validação sexo
		if (form.getSexo() == null) {
			throw new CampoFormularioInvalidoException("Sexo inválido.");
		}

	}

	/**
	 * Função para validação dos campos "nome" e "nome social" do
	 * {@link PacienteForm}.
	 * 
	 * @param nome            para validação
	 * @param mensagemExcecao mensagem para ser exibida em caso de exceção
	 * 
	 * @throws CampoFormularioInvalidoException quando nome passado não for válido
	 */
	private void validacaoNome(String nome, String mensagemExcecao) {
		if (nome == null || nome.isEmpty() || !nome.matches(
				"^[A-Za-z]+([\\']{1}[A-Za-z]+([ ]{1})?)?(([A-Za-z]+[\\']{1})?([A-Za-z]*)?([A-Za-z]+([ ]{1})?))*$")) {
			throw new CampoFormularioInvalidoException(mensagemExcecao);
		}
	}

}
