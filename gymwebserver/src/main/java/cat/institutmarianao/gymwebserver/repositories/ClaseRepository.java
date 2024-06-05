
package cat.institutmarianao.gymwebserver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cat.institutmarianao.gymwebserver.model.Clase;
import cat.institutmarianao.gymwebserver.model.User;

public interface ClaseRepository extends JpaRepository<Clase, Long>, JpaSpecificationExecutor<Clase> {
    
	List<Clase> findByMonitor(User monitor);
}