package cat.institutmarianao.gymwebserver.model.convert.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cat.institutmarianao.gymwebserver.model.Clase;
import cat.institutmarianao.gymwebserver.model.Reserva;
import cat.institutmarianao.gymwebserver.model.User;
import cat.institutmarianao.gymwebserver.model.dto.ReservaDto;
import cat.institutmarianao.gymwebserver.services.ClaseService;
import cat.institutmarianao.gymwebserver.services.UserService;

@Component
public class ReservaDtoToReservaConverter implements Converter<ReservaDto, Reserva>{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ClaseService claseService;
	
	@Override
	public Reserva convert(ReservaDto reservaDto) {
		if(reservaDto == null) {
			return null;
		}	
		User reservaUser = userService.getById(reservaDto.getUser());
		
		Clase reservaClase = claseService.getById(reservaDto.getClase());
		
		if (reservaUser == null || reservaClase == null) {
            throw new IllegalArgumentException("User or Class not found");
        }
		
		Reserva reserva = new Reserva();
		
		BeanUtils.copyProperties(reservaDto, reserva);
		
		reserva.setClase(reservaClase);
		reserva.setDateTime(reservaDto.getDateTime());
		reserva.setUser(reservaUser);
		
		return reserva;
	}
}
