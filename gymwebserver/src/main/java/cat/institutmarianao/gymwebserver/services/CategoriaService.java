package cat.institutmarianao.gymwebserver.services;

import java.util.List;

import cat.institutmarianao.gymwebserver.model.Categoria;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public interface CategoriaService {

    List<Categoria> findAll();

    Categoria getById(@Positive Long id);

    Categoria save(@Valid Categoria categoria);

    void deleteById(@NotBlank Long id);

}