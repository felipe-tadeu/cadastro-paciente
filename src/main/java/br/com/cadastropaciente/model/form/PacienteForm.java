package br.com.cadastropaciente.model.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.cadastropaciente.model.enums.SexoEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * Formulário para entidade Paciente.
 * 
 * @author Felipe Tadeu
 *
 */
@Getter
@Setter
public class PacienteForm {
	
	private String nome;
	
    private String cpf;
    
    private boolean seDesejaUsarNomeSocial;
    
    private String nomeSocial;
    
    private Long numeroProntuario;
    
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dataNascimento;
    
    private SexoEnum sexo;
    
    private EnderecoForm endereco;

}
