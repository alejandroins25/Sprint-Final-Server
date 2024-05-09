package cat.institutmarianao.gymwebserver.model;

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
public class Clase implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @GeneratedValue
    @Id
    @EqualsAndHashCode.Include
    private Long id;

    private Integer duracion;

    private Timestamp fechaHora;

    @ManyToOne
    @JoinColumn(name = "id_monitor")
    private User monitor;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_sala")
    private Sala sala;

    public Clase() {}

}