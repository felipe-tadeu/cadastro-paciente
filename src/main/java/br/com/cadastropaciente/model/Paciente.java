package br.com.cadastropaciente.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.cadastropaciente.model.enums.SexoEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidade Paciente.
 * 
 * @author Felipe Tadeu
 *
 */
@Entity
@Getter
@Setter
@Table(name = "paciente")
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(unique = true, nullable = false)
	private String cpf;

	@Column(name = "se_deseja_usar_nome_social")
	private boolean seDesejaUsarNomeSocial;

	@Column(name = "nome_social")
	private String nomeSocial;

	@Column(name = "numero_prontuario", unique = true, nullable = false)
	private Long numeroProntuario;

	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	@Column(name="sexo")
	@Enumerated(EnumType.STRING)
	private SexoEnum sexo;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_endereco", referencedColumnName = "id")
	private Endereco endereco;

}
