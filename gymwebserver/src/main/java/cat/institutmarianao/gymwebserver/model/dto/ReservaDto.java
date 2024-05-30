package cat.institutmarianao.gymwebserver.model.dto;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
@Table(name = "reservas")
public class ReservaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue
    @Id
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserDto idUser;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "id_clase")
    private ClaseDto idClase;

    public ReservaDto() {}
}