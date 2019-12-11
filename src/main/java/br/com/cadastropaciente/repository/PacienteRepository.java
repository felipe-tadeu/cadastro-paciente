package br.com.cadastropaciente.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

	/* Busca o menor número de prontuário disponível. Adaptado de: https://stackoverflow.com/a/5016969 */
	@Query("select min(p1.numeroProntuario + 1) from Paciente p1 left join Paciente p2 on (p1.numeroProntuario) + 1 = p2.numeroProntuario where p2.numeroProntuario is NULL")
	Optional<Long> obterNumeroProntuario();

}
