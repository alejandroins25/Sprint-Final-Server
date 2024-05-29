package cat.institutmarianao.gymwebserver.services;

import java.util.List;

import cat.institutmarianao.gymwebserver.model.Sala;

public interface SalaService {
	
    List<Sala> findAll();
    
    Sala findById(Long id);
    
    Sala save(Sala sala);
    
    void delete(Long id);
}