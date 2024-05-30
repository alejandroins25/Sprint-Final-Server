package cat.institutmarianao.gymwebserver.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "clases")
public class ClaseDto implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @GeneratedValue
    @Id
    @EqualsAndHashCode.Include
    private Long id;

    private Integer duracion;

    private Timestamp fechaHora;

    @ManyToOne
    @JoinColumn(name = "id_monitor")
    private UserDto monitor;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaDto categoria;

    @ManyToOne
    @JoinColumn(name = "id_sala")
    private SalaDto sala;

    public ClaseDto() {}

}