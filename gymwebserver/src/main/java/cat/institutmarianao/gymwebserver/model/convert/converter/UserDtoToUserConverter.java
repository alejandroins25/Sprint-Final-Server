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
        User user = null;
        if (userDto instanceof ResponsableDto) {
            user = new Responsable();
        } else if (userDto instanceof MonitorDto) {
            user = new Monitor();
        } else if (userDto instanceof ClienteDto) {
            user = new Cliente();
        }
        if (user != null) {
            BeanUtils.copyProperties(userDto, user);
        }
        return user;
    }
}
