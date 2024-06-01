package cat.institutmarianao.gymwebserver.model.dto;

import java.io.Serializable;

import cat.institutmarianao.gymwebserver.model.Categoria;
import cat.institutmarianao.gymwebserver.model.Sala;
import cat.institutmarianao.gymwebserver.model.User;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "classes")
public class ClaseDto implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private Long id;
    
    private String name;
    
    private Integer duration;
    
    private Integer valoration;

    private User monitor;

    private Categoria categoria;

    private Sala sala;

    public ClaseDto() {}

}