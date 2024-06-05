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
        UserDto userDto = null;
        if (user instanceof Responsable) {
            userDto = new ResponsableDto();
        } else if (user instanceof Monitor) {
            userDto = new MonitorDto();
        } else if (user instanceof Cliente) {
            userDto = new ClienteDto();
        }
        if (userDto != null) {
            BeanUtils.copyProperties(user, userDto);
        }
        return userDto;
    }
}
