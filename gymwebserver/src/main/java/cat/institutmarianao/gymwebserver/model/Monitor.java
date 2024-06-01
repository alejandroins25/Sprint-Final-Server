package cat.institutmarianao.gymwebserver.model;

import java.io.Serializable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue(User.monitor)
/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)
public class Monitor extends User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
    @JoinColumn(name = "roomId")
	@NotNull
	private Sala sala;

}
