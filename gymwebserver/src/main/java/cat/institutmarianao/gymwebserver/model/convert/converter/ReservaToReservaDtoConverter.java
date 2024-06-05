package cat.institutmarianao.gymwebserver.model.convert.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cat.institutmarianao.gymwebserver.model.Reserva;
import cat.institutmarianao.gymwebserver.model.dto.ReservaDto;

@Component
public class ReservaToReservaDtoConverter implements Converter<Reserva, ReservaDto>{

	@Override
	public ReservaDto convert(Reserva reserva) {
		if(reserva== null) {
			return null;
		}
		
		ReservaDto reservaDto = new ReservaDto();
		
		BeanUtils.copyProperties(reserva, reservaDto);
		
		if (reserva.getClase() != null) {
            reservaDto.setClase(reserva.getClase().getId());
        }
        if (reserva.getUser() != null) {
            reservaDto.setUser(reserva.getUser().getId());
        }
        
		reservaDto.setDateTime(reserva.getDateTime());
		
		return reservaDto;
	}

}
