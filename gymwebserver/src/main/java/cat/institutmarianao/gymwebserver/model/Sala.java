package cat.institutmarianao.gymwebserver.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "rooms")
public class Sala implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private static final int MAX_CAPACITY = 150;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    
    @NotBlank
    @Column(name = "name")
    private String name;
    
    @NotBlank
    @Column(name = "capacity")
    @Size(max = MAX_CAPACITY)
    private Integer capacity;

}