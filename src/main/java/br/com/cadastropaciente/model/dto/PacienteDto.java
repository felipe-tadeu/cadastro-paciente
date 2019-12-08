package br.com.cadastropaciente.model.dto;

import java.time.LocalDate;

import br.com.cadastropaciente.model.Paciente;
import br.com.cadastropaciente.model.enums.SexoEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para a entidade {@link Paciente}.
 * 
 * @author Felipe Tadeu
 *
 */
@Getter
@Setter
public class PacienteDto {

	private Long id;

	private String nome;

	private String cpf;

	private boolean seDesejaUsarNomeSocial;

	private String nomeSocial;

	private Long numeroProntuario;

	private LocalDate dataNascimento;

	private SexoEnum sexo;

	private EnderecoDto endereco;

}
