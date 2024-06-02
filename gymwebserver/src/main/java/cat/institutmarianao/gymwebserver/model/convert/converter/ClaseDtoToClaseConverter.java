package cat.institutmarianao.gymwebserver.model.convert.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import cat.institutmarianao.gymwebserver.model.Clase;
import cat.institutmarianao.gymwebserver.model.User;
import cat.institutmarianao.gymwebserver.model.dto.ClaseDto;
import cat.institutmarianao.gymwebserver.services.UserService;

public class ClaseDtoToClaseConverter implements Converter<ClaseDto, Clase>{
	
	@Autowired
	private UserService userService;

	@Override
	public Clase convert(ClaseDto claseDto) {
		if(claseDto == null) {
			return null;
		}	
		User classMonitor = userService.getByUsername(claseDto.getMonitor());
		
		Clase clase = new Clase();
		
		BeanUtils.copyProperties(claseDto, clase);
		
		clase.setCategoria(claseDto.getCategoria());
		clase.setDuration(claseDto.getDuration());
		clase.setName(claseDto.getName());
		clase.setSala(claseDto.getSala());
		clase.setValoration(claseDto.getValoration());
		clase.setMonitor(classMonitor);
		
		return clase;
	}

}
