package cat.institutmarianao.gymwebserver.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import cat.institutmarianao.gymwebserver.model.User;
import io.micrometer.common.lang.Nullable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    
    public static final int MIN_AGE = 16;
    
    @Nullable
    protected Long id;
    
    @NotBlank(message = "Username cannot be blank")
    protected String username;
    
    @NotBlank(message = "Password cannot be blank")
    protected String passwd;
    
    @NotBlank(message = "Name cannot be blank")
    protected String name;
    
    @NotBlank(message = "DNI cannot be blank")
    protected String dni;
    
    @Email(message = "Email should be valid")
    protected String email;
    
    @Min(value = MIN_AGE, message = "Age must be positive")
    protected int age;

    protected User.Role role;
    
}