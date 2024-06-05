package cat.institutmarianao.gymwebserver.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "reservations")
public class ReservaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private Long id;

    private Long user;

    private Long clase;
    
    @Column(name = "date_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp dateTime;

    public ReservaDto() {}
}
