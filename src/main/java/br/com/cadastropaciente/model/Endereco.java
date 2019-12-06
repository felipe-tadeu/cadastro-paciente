package br.com.cadastropaciente.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entidade Endereço.
 * 
 * @author Felipe Tadeu
 *
 */
@Getter
@Setter
@Entity
@Table(name = "endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID uuid;

	private Long cep;

	private String endereco;

	private Long numero;

	private String complemento;
	
	@OneToOne(mappedBy = "endereco")
	private Paciente paciente;

}
