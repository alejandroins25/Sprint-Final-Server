package cat.institutmarianao.gymwebserver.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)
public class Responsable extends User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
    @JoinColumn(name = "id")
	@NotNull
	private Sala sala;
	
	

}


