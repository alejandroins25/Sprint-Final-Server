package cat.institutmarianao.gymwebserver.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.gymwebserver.exception.NotFoundException;
import cat.institutmarianao.gymwebserver.model.Clase;
import cat.institutmarianao.gymwebserver.repositories.ClaseRepository;
import cat.institutmarianao.gymwebserver.services.ClaseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class ClaseServiceImpl implements ClaseService {

    @Autowired
    private ClaseRepository claseRepository;

    @Override
    public List<Clase> findAll() {
        return claseRepository.findAll();
    }

    @Override
    public Clase getById(@Positive Long id) {
        return claseRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Clase save(@Valid Clase clase) {
        return claseRepository.saveAndFlush(clase);
    }

    @Override
    public void deleteById(@NotBlank Long id) {
        claseRepository.deleteById(id);
    }
}
