package cat.institutmarianao.gymwebserver.model.convert.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cat.institutmarianao.gymwebserver.model.Clase;
import cat.institutmarianao.gymwebserver.model.dto.ClaseDto;

@Component
public class ClaseToClaseDtoConverter implements Converter<Clase, ClaseDto>{

	@Override
	public ClaseDto convert(Clase clase) {
		if(clase == null) {
			return null;
		}
		
		ClaseDto claseDto = new ClaseDto();
		
		BeanUtils.copyProperties(clase, claseDto);
		
		claseDto.setCategoria(clase.getCategoria().getId());
		claseDto.setDuration(clase.getDuration());
		claseDto.setMonitor(clase.getMonitor().getId());
		claseDto.setName(clase.getName());
		claseDto.setSala(clase.getSala().getId());
		claseDto.setValoration(clase.getValoration());
		
		return claseDto;
	}

}
