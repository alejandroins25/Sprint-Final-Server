package cat.institutmarianao.gymwebserver.model;

import java.io.Serializable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue(User.cliente)
/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)
public class Cliente extends User implements Serializable{
	
	private static final long serialVersionUID = 1L;

}
