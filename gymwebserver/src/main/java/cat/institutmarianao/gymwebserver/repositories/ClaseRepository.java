
package cat.institutmarianao.gymwebserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.institutmarianao.gymwebserver.model.Clase;

public interface ClaseRepository extends JpaRepository<Clase, Long> {
    
}