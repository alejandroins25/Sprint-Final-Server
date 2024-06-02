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
public class UserDtoToUserConverter implements Converter<UserDto, User> {

	@Override
	public User convert(UserDto userDto) {
		if (userDto instanceof ResponsableDto responsableDto) {
			// Includes Supervisor
			Responsable responsable = new Responsable();
			copyCommonProperties(responsableDto, responsable);

			return responsable;
		}
		if (userDto instanceof MonitorDto monitorDto) {
			Monitor monitor = new Monitor();
			copyCommonProperties(monitorDto, monitor);

			// TODO Copy office
			return monitor;
		}
		if (userDto instanceof ClienteDto clienteDto) {
			// Includes Supervisor
			Cliente cliente = new Cliente();
			copyCommonProperties(clienteDto, cliente);

			// TODO Copy company
			return cliente;
		}
		return null;
	}

	private void copyCommonProperties(UserDto userDto, User user) {
		BeanUtils.copyProperties(userDto, user);
	}
}
