package cat.institutmarianao.gymwebserver.services;

import java.util.List;

import cat.institutmarianao.gymwebserver.model.Clase;

public interface ClaseService {
	
    List<Clase> findAll();
    
    Clase findById(Long id);
    
    Clase save(Clase clase);
    
    void delete(Long id);
}
