package cat.institutmarianao.gymwebserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.institutmarianao.gymwebserver.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long> {
    // No need to define custom methods, Spring Data JPA provides basic CRUD operations
}