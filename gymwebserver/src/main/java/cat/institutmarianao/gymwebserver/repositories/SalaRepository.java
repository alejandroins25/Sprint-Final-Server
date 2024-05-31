package cat.institutmarianao.gymwebserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cat.institutmarianao.gymwebserver.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long> , JpaSpecificationExecutor<Sala> {
}