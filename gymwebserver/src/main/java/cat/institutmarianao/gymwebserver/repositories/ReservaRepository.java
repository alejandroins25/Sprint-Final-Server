package cat.institutmarianao.gymwebserver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cat.institutmarianao.gymwebserver.model.Reserva;
import cat.institutmarianao.gymwebserver.model.User;

public interface ReservaRepository extends JpaRepository<Reserva, Long> , JpaSpecificationExecutor<Reserva> {
	
	List<Reserva> findByUser(User user);

}