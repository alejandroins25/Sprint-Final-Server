package cat.institutmarianao.gymwebserver.services;

import java.util.List;

import cat.institutmarianao.gymwebserver.model.Sala;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public interface SalaService {
	
	List<Sala> findAll();
    
    Sala getById(@Positive Long id);
    
    Sala save(@Valid Sala sala);
    
    void delete(@NotBlank Long id);
}