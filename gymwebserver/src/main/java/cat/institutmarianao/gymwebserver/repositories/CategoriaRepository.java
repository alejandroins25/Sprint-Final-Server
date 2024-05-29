package cat.institutmarianao.gymwebserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.institutmarianao.gymwebserver.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
