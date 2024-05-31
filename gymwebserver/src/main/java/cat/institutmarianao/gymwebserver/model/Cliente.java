package cat.institutmarianao.gymwebserver.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)
public class Cliente extends User implements Serializable{
	
	private static final long serialVersionUID = 1L;

}
