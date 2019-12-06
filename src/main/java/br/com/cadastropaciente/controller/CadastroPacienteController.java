package br.com.cadastropaciente.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastropaciente.model.form.PacienteForm;
import io.swagger.annotations.ApiOperation;

/**
 * Controller do Cadastro de Paciente.
 * 
 * @author Felipe Tadeu
 *
 */
@RestController
@RequestMapping("/v1/api/cadastro-paciente")
public class CadastroPacienteController {
	
	@PostMapping
	@ApiOperation("Método para cadastrar um novo paciente.")
	public ResponseEntity<Void> cadastrarPaciente(PacienteForm form) {
		
		System.out.println(form);
		
		return ResponseEntity.ok(null);
		
	}

}
