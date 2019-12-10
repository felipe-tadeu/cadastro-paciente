package br.com.cadastropaciente.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cadastropaciente.model.Paciente;

/**
 * Repository para a entidade {@link Paciente}.
 * 
 * @author Felipe Tadeu
 *
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	
	/* Busca paciente pelo CPF. */
	Optional<Paciente> findByCpf(String cpf);
	
	/* Busca paciente pelo número de prontuário. */
	Optional<Paciente> findByNumeroProntuario(Long numeroProntuario);
	

}
