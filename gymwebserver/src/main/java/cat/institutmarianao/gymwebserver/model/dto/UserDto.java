package cat.institutmarianao.gymwebserver.model.dto;

import java.io.Serializable;

import cat.institutmarianao.gymwebserver.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Swagger */
@Schema(oneOf = { ResponsableDto.class, MonitorDto.class, ClienteDto.class }, discriminatorProperty = "role")
/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private Long id;

    private String username;

    private String passwd;

    private String nombre;

    private String dni;

    private String email;

    private User.Role role;

    private Boolean isMonitor;

    
}