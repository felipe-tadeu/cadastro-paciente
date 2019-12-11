package br.com.cadastropaciente.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cadastropaciente.model.dto.PacienteDto;
import br.com.cadastropaciente.model.form.PacienteForm;
import br.com.cadastropaciente.service.CadastroPacienteService;
import io.swagger.annotations.ApiOperation;

/**
 * Controller do Cadastro de Paciente.
 * 
 * @author Felipe Tadeu
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/v1/api/cadastro-paciente")
public class CadastroPacienteController {

	/* DEPENDÊNCIAS */
	private final CadastroPacienteService service;

	/* CONSTRUTOR */
	public CadastroPacienteController(CadastroPacienteService service) {
		this.service = service;
	}

	/**
	 * Endpoint para cadastrar um novo paciente.
	 * 
	 * @param form {@link PacienteForm} com dados do paciente.
	 * @return {@link PacienteDto} com dados do paciente cadastrado.
	 * 
	 * @see {@link CadastroPacienteService#cadastrarPaciente(PacienteForm) Cadastrar
	 *      Paciente}
	 */
	@PostMapping
	@ApiOperation("Cadastra um novo paciente.")
	public ResponseEntity<PacienteDto> cadastrarPaciente(@RequestBody PacienteForm form) {
		PacienteDto pacienteCadastradoDto = this.service.cadastrarPaciente(form);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(pacienteCadastradoDto.getId()).toUri();
		return ResponseEntity.created(uri).body(pacienteCadastradoDto);

	}

	/**
	 * Endpoint para obter o menor número de prontuário disponível.
	 * 
	 * @return menor número de prontuário disponível.
	 * 
	 * @see {@link CadastroPacienteService#obterNumeroPronturario() Obter Número de
	 *      Prontuário}
	 */
	@GetMapping("/obter-numero-prontuario")
	@ApiOperation("Obtém o menor número de prontuário disponível.")
	public ResponseEntity<Long> obterNumeroProntuario() {
		return ResponseEntity.ok(this.service.obterNumeroPronturario());
	}

	/**
	 * Endpoint para listar todos os pacientes cadastrados.
	 * 
	 * @return lista de {@link PacienteDto}
	 * 
	 * @see {@link CadastroPacienteService#listarPacientes() Listar Pacientes}
	 */
	@GetMapping
	@ApiOperation("Lista todos os pacientes cadastrados.")
	public ResponseEntity<List<PacienteDto>> listarPacientes() {
		return ResponseEntity.ok(this.service.listarPacientes());
	}

	/**
	 * Endpoint para detalhar dados de um paciente.
	 * 
	 * @param id do paciente para detalhamento de dados
	 * @return {@link PacienteDto} com dados do paciente
	 * 
	 * @see {@link CadastroPacienteService#detalharDadosPaciete(Long) Detalhar Dados
	 *      do Paciente}
	 */
	@GetMapping("/{id}")
	@ApiOperation("Obtém dados de um paciente.")
	public ResponseEntity<PacienteDto> detalharDadosPaciete(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.detalharDadosPaciente(id));
	}

	/**
	 * Endpoint para atualizar dados de um paciente.
	 * 
	 * @param id   do paciente para atualização
	 * @param form {@link PacienteForm} com dados para atualização
	 * @return {@link PacienteDto} com dados da entidade atualizados
	 * 
	 * @see {@link CadastroPacienteService#atualizarDadosPaciente(Long, PacienteForm)
	 *      Atualizar Dados do Paciente}
	 */
	@PutMapping("/{id}")
	@ApiOperation("Atualiza dados de um paciente.")
	public ResponseEntity<PacienteDto> atualizarDadosPaciente(@PathVariable Long id, @RequestBody PacienteForm form) {
		return ResponseEntity.ok(this.service.atualizarDadosPaciente(id, form));
	}

	/**
	 * Endpoint para deletar paciente cadastrado.
	 * 
	 * @param id do paciente a ser removido
	 * 
	 * @see {@link CadastroPacienteService#deletarPaciente(Long) Deletar Paciente}
	 */
	@DeleteMapping("/{id}")
	@ApiOperation("Deleta um paciente.")
	public ResponseEntity<Void> deletarPaciente(@PathVariable Long id) {
		this.service.deletarPaciente(id);
		return ResponseEntity.ok().build();
	}

}
