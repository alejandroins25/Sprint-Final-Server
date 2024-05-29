package cat.institutmarianao.gymwebserver.services;

import java.util.List;

import cat.institutmarianao.gymwebserver.model.Reserva;

public interface ReservaService {

    List<Reserva> findAll();

    Reserva findById(Long id);

    Reserva save(Reserva reserva);

    void deleteById(Long id);

}
