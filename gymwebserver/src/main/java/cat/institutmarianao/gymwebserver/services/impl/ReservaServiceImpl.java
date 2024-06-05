package cat.institutmarianao.gymwebserver.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.gymwebserver.exception.NotFoundException;
import cat.institutmarianao.gymwebserver.model.Reserva;
import cat.institutmarianao.gymwebserver.repositories.ReservaRepository;
import cat.institutmarianao.gymwebserver.services.ReservaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva getById(@NotNull @Positive Long id) {
        return reservaRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Reserva save(@Valid Reserva reserva) {
        return reservaRepository.saveAndFlush(reserva);
    }

    @Override
    public void deleteById(@NotNull Long id) {
        reservaRepository.deleteById(id);
    }
}
