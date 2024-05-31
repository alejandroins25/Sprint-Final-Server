package cat.institutmarianao.gymwebserver.model.dto;

import java.io.Serializable;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)
public class MonitorDto extends UserDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

}
