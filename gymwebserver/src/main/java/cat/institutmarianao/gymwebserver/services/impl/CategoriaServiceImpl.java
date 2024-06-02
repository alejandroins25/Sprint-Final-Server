package cat.institutmarianao.gymwebserver.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.gymwebserver.exception.NotFoundException;
import cat.institutmarianao.gymwebserver.model.Categoria;
import cat.institutmarianao.gymwebserver.repositories.CategoriaRepository;
import cat.institutmarianao.gymwebserver.services.CategoriaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria getById(@Positive Long id) {
        return categoriaRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Categoria save(@Valid Categoria categoria) {
        return categoriaRepository.saveAndFlush(categoria);
    }

    @Override
    public void deleteById(@NotBlank Long id) {
        categoriaRepository.deleteById(id);
    }
}
