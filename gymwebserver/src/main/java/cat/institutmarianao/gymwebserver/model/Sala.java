package cat.institutmarianao.gymwebserver.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "salas")
public class Sala implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "categoria")
    private String categoria;

    @NotBlank
    @Column(name = "planta")
    private String planta;

    @NotBlank
    @Column(name = "numero")
    private String numero;

    @Column(name = "nota")
    private String nota;

    @Column(name = "aforo")
    private int aforo;

    @ManyToOne
    @JoinColumn(name = "id_responsable")
    private User idResponsable;

}