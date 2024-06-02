package cat.institutmarianao.gymwebserver.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import cat.institutmarianao.gymwebserver.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Swagger */
@Schema(oneOf = { ResponsableDto.class, MonitorDto.class, ClienteDto.class }, discriminatorProperty = "role")
/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = MonitorDto.class, name = "monitor"),
    @JsonSubTypes.Type(value = ClienteDto.class, name = "cliente"),
    @JsonSubTypes.Type(value = ResponsableDto.class, name = "responsable")
})
public abstract class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String username;

    protected String passwd;

    protected String name;

    protected String dni;

    protected String email;
    
    protected int age;

    protected User.Role role;
    
}