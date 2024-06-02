package cat.institutmarianao.gymwebserver.services;

import java.util.List;

import cat.institutmarianao.gymwebserver.model.Clase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public interface ClaseService {
	
    List<Clase> findAll();
    
    Clase getById(@Positive Long id);
    
    Clase save(@Valid Clase clase);
    
    void deleteById(@NotBlank Long id);
}
