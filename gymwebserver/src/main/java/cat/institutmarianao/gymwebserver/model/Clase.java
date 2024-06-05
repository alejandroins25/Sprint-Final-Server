package cat.institutmarianao.gymwebserver.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
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
    
    public static final int MAX_DURATION = 120;
    public static final int MAX_VALORATION = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    
    @NotBlank
    private String name;
    
    @NotNull
    @Max(value = MAX_DURATION)
    private Integer duration;
    
    @NotNull
    @Max(value = MAX_VALORATION)
    private Integer valoration;

    @ManyToOne
    @JoinColumn(name = "monitor_id", nullable = true)
    private User monitor;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Sala sala;
    
    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;

    public Clase() {}
}