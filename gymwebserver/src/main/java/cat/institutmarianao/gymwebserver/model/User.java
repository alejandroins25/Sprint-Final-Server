package cat.institutmarianao.gymwebserver.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
/*JPA*/
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="role", discriminatorType=DiscriminatorType.STRING)
@Table(name = "users")
public abstract class User implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String monitor = "monitor";
    public static final String responsable = "responsable";
    public static final String cliente = "cliente";

    public enum Role {
    	monitor, responsable, cliente
    }

    public static final int MIN_USERNAME = 2;
    public static final int MAX_USERNAME = 25;
    public static final int MIN_AGE = 16;
    public static final int MAX_AGE = 110;
    public static final int MIN_PASSWORD = 10;
    public static final int MIN_DNI = 9;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Size(min = MIN_USERNAME, max = MAX_USERNAME)
    @NotBlank
    @Column(name = "username")
    private String username;

    @NotNull
    @Size(min = MIN_PASSWORD)
    @Column(name = "passwd")
    protected String passwd;

    @NotBlank
    @Column(name = "name")
    protected String name;

    @NotBlank
    @Column(name = "dni")
    protected String dni;

    @NotBlank
    @Column(name = "email")
    protected String email;
    
    @NotNull
    @Size(min = MIN_AGE, max = MAX_AGE)
    @Column(name = "age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "role",insertable=false, updatable=false)
    protected Role role;
    
}