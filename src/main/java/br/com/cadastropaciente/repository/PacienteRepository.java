package br.com.cadastropaciente.repository;

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

}
