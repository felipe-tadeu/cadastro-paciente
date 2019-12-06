package br.com.cadastropaciente.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID uuid;

	private String nome;

	private Long cpf;

	@Column(name = "se_deseja_usar_nome_social")
	private boolean seDesejaUsarNomeSocial;

	@Column(name = "nome_social")
	private String nomeSocial;

	@Column(name = "numero_prontuario")
	private Long numeroProntuario;

	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	@Enumerated(EnumType.STRING)
	private SexoEnum sexo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_endereco", referencedColumnName = "uuid")
	private Endereco endereco;

}
