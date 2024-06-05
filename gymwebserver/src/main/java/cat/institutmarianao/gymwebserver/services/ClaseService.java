package cat.institutmarianao.gymwebserver.services;

import java.util.List;

import cat.institutmarianao.gymwebserver.model.Clase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface ClaseService {
	
    List<Clase> findAll();
    
    Clase getById(@NotNull @Positive Long id);
    
    Clase save(@Valid Clase clase);
    
    void deleteById(@NotNull @Positive Long id);
}
