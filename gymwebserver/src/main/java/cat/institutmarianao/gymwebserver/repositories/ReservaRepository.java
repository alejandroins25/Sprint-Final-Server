package cat.institutmarianao.gymwebserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.institutmarianao.gymwebserver.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}