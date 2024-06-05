package cat.institutmarianao.gymwebserver.services;

import java.util.List;

import cat.institutmarianao.gymwebserver.model.Reserva;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface ReservaService {

    List<Reserva> findAll();

    Reserva getById(@NotNull @Positive Long id);

    Reserva save(@Valid Reserva reserva);

    void deleteById(@NotNull Long id);

}
