package cat.institutmarianao.gymwebserver.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String MONITOR = "MONITOR";
    public static final String RESPONSABLE = "RESPONSABLE";

    public enum Rol {
        MONITOR, RESPONSABLE
    }

    public static final int MIN_USERNAME = 2;
    public static final int MAX_USERNAME = 25;
    public static final int MIN_PASSWORD = 10;
    public static final int MIN_DNI = 9;

    @GeneratedValue
    @Id
    private Long id;

    @Size(min = MIN_USERNAME, max = MAX_USERNAME)
    @NotBlank
    private String username;

    @NotNull
    @Size(min = MIN_PASSWORD)
    @Column(name = "passwd")
    private String passwd;

    @NotBlank
    @Column(name = "nombre")
    private String nombre;

    @NotBlank
    @Column(name = "dni")
    private String dni;

    @NotBlank
    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private Rol rol;

    @Column(name = "isMonitor")
    private Boolean isMonitor;
    
    public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority("ROLE_" + getRol()));
		return list;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Boolean getIsMonitor() {
		return isMonitor;
	}

	public void setIsMonitor(Boolean isMonitor) {
		this.isMonitor = isMonitor;
	}
	
	public User(String username, String passwd, String nombre, String email, Rol rol, Boolean isMonitor) {
		this.username = username;
		this.passwd = passwd;
		this.nombre = nombre;
		this.email = email;
		this.rol = rol;
		this.isMonitor = isMonitor;
	}
    
}