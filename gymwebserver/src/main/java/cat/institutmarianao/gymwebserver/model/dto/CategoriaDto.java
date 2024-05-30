package cat.institutmarianao.gymwebserver.model.dto;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "categorias")
public class CategoriaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue
    @Id
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @Column(name = "nombre")
    private String nombre;

    public CategoriaDto() {}

}