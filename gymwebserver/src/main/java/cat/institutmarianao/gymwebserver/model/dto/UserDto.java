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
public abstract class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String username;

    protected String passwd;

    protected String name;

    protected String dni;

    protected String email;
    
    protected Integer age;

    protected User.Role role;
    
    protected String location;

    
}