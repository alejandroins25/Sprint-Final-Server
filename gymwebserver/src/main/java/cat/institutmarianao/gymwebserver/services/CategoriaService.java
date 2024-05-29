package cat.institutmarianao.gymwebserver.services;

import java.util.List;

import cat.institutmarianao.gymwebserver.model.Categoria;

public interface CategoriaService {

    List<Categoria> getAllCategories();

    Categoria getById(Long id);

    Categoria save(Categoria categoria);

    void deleteById(Long id);

}