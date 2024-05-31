package cat.institutmarianao.gymwebserver.model.convert.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cat.institutmarianao.gymwebserver.model.Responsable;
import cat.institutmarianao.gymwebserver.model.User;
import cat.institutmarianao.gymwebserver.model.dto.ResponsableDto;
import cat.institutmarianao.gymwebserver.model.dto.UserDto;
import cat.institutmarianao.shipmentsws.model.Company;
import cat.institutmarianao.shipmentsws.model.Courier;
import cat.institutmarianao.shipmentsws.model.LogisticsManager;
import cat.institutmarianao.shipmentsws.model.Receptionist;
import cat.institutmarianao.shipmentsws.model.dto.CourierDto;
import cat.institutmarianao.shipmentsws.model.dto.LogisticsManagerDto;
import cat.institutmarianao.shipmentsws.model.dto.ReceptionistDto;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {
	
	@Override
	public UserDto convert(User user) {
		if (user instanceof Responsable responsable) {
			ResponsableDto logisticsManagerDto = new ResponsableDto();

			copyCommonProperties(responsable, logisticsManagerDto);

			logisticsManagerDto.setOfficeId(responsable.get().getId());
			logisticsManagerDto.setPlace(responsable.getPlace());

			logisticsManagerDto.setLocation(
					String.format("%s (%s)", responsable.getOffice().getName(), responsable.getPlace()));

			return logisticsManagerDto;
		}
		if (user instanceof Receptionist receptionist) {
			ReceptionistDto receptionistDto = new ReceptionistDto();

			copyCommonProperties(receptionist, receptionistDto);

			receptionistDto.setOfficeId(receptionist.getOffice().getId());
			receptionistDto.setPlace(receptionist.getPlace());

			receptionistDto
					.setLocation(String.format("%s (%s)", receptionist.getOffice().getName(), receptionist.getPlace()));

			return receptionistDto;
		}
		if (user instanceof Courier courier) {
			CourierDto courierDto = new CourierDto();

			copyCommonProperties(courier, courierDto);

			Company company = courier.getCompany();
			courierDto.setCompanyId(company.getId());

			courierDto.setLocation(company.getName());

			return courierDto;
		}
		return null;
	}

	private void copyCommonProperties(User user, UserDto userDto) {
		BeanUtils.copyProperties(user, userDto);
	}
}
