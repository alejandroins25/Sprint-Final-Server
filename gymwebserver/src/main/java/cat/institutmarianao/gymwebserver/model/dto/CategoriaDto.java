package cat.institutmarianao.gymwebserver.model.dto;

import java.io.Serializable;

import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "categories")
public class CategoriaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private Long id;

    private String name;
    
    private String desc;

    public CategoriaDto() {}

}
