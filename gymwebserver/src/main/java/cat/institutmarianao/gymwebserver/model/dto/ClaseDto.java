package cat.institutmarianao.gymwebserver.model.dto;

import java.io.Serializable;

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

    private Long monitor;

    private Long categoria;

    private Long sala;

    public ClaseDto() {}

}