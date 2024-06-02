package cat.institutmarianao.gymwebserver.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.gymwebserver.exception.NotFoundException;
import cat.institutmarianao.gymwebserver.model.Sala;
import cat.institutmarianao.gymwebserver.repositories.SalaRepository;
import cat.institutmarianao.gymwebserver.services.SalaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class SalaServiceImpl implements SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Override
    public List<Sala> findAll() {
        return salaRepository.findAll();
    }

    @Override
    public Sala getById(@Positive Long id) {
        return salaRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Sala save(@Valid Sala sala) {
        return salaRepository.saveAndFlush(sala);
    }

    @Override
    public void delete(@NotBlank Long id) {
        salaRepository.deleteById(id);
    }
}
