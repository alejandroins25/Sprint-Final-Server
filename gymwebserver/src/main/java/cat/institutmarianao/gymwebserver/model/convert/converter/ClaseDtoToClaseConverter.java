package cat.institutmarianao.gymwebserver.model.convert.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cat.institutmarianao.gymwebserver.model.Categoria;
import cat.institutmarianao.gymwebserver.model.Clase;
import cat.institutmarianao.gymwebserver.model.Sala;
import cat.institutmarianao.gymwebserver.model.User;
import cat.institutmarianao.gymwebserver.model.dto.ClaseDto;
import cat.institutmarianao.gymwebserver.services.CategoriaService;
import cat.institutmarianao.gymwebserver.services.SalaService;
import cat.institutmarianao.gymwebserver.services.UserService;

@Component
public class ClaseDtoToClaseConverter implements Converter<ClaseDto, Clase>{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private SalaService salaService;

	@Override
	public Clase convert(ClaseDto claseDto) {
		if(claseDto == null) {
			return null;
		}	
		User classMonitor = userService.getById(claseDto.getMonitor());
		
		Categoria classCategory = categoriaService.getById(claseDto.getCategoria());
		
		Sala classSala = salaService.getById(claseDto.getSala());
		
		Clase clase = new Clase();
		
		BeanUtils.copyProperties(claseDto, clase);
		
		clase.setCategoria(classCategory);
		clase.setDuration(claseDto.getDuration());
		clase.setName(claseDto.getName());
		clase.setSala(classSala);
		clase.setValoration(claseDto.getValoration());
		clase.setMonitor(classMonitor);
		
		return clase;
	}

}
