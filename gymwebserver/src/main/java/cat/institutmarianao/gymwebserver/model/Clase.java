package cat.institutmarianao.gymwebserver.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "classes")
public class Clase implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    
    @NotBlank
    private String name;
    
    @NotNull
    private Integer duration;
    
    @NotNull
    private Integer valoration;

    @ManyToOne
    @JoinColumn(name = "monitor_id")
    private User monitor;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Sala sala;

    public Clase() {}
}