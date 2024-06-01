package cat.institutmarianao.gymwebserver.model.dto;

import java.io.Serializable;
import java.security.Timestamp;

import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "reservations")
public class ReservaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private Long id;

    private UserDto user;

    private ClaseDto clase;
    
    private Timestamp dateTime;

    public ReservaDto() {}
}
