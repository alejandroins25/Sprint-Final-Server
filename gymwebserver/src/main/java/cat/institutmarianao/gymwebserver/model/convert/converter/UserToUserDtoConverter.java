package cat.institutmarianao.gymwebserver.model.convert.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cat.institutmarianao.gymwebserver.model.Cliente;
import cat.institutmarianao.gymwebserver.model.Monitor;
import cat.institutmarianao.gymwebserver.model.Responsable;
import cat.institutmarianao.gymwebserver.model.User;
import cat.institutmarianao.gymwebserver.model.dto.ClienteDto;
import cat.institutmarianao.gymwebserver.model.dto.MonitorDto;
import cat.institutmarianao.gymwebserver.model.dto.ResponsableDto;
import cat.institutmarianao.gymwebserver.model.dto.UserDto;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {
	
	@Override
	public UserDto convert(User user) {
		if (user instanceof Responsable responsable) {
			ResponsableDto responsableDto = new ResponsableDto();

			copyCommonProperties(responsable, responsableDto);

			return responsableDto;
		}
		if (user instanceof Monitor monitor) {
			MonitorDto monitorDto = new MonitorDto();

			copyCommonProperties(monitor, monitorDto);

			return monitorDto;
		}
		if (user instanceof Cliente cliente) {
			ClienteDto clienteDto = new ClienteDto();

			copyCommonProperties(cliente, clienteDto);


			return clienteDto;
		}
		return null;
	}

	private void copyCommonProperties(User user, UserDto userDto) {
		BeanUtils.copyProperties(user, userDto);
	}
}
