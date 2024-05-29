package cat.institutmarianao.gymwebserver.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.gymwebserver.exception.NotFoundException;
import cat.institutmarianao.gymwebserver.model.User;
import cat.institutmarianao.gymwebserver.model.User.Role;
import cat.institutmarianao.gymwebserver.repositories.UserRepository;
import cat.institutmarianao.gymwebserver.services.UserService;
import cat.institutmarianao.gymwebserver.specifications.UserWithFullName;
import cat.institutmarianao.gymwebserver.specifications.UserWithRole;
import cat.institutmarianao.gymwebserver.validation.groups.OnUserCreate;
import cat.institutmarianao.gymwebserver.validation.groups.OnUserUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll(Role[] roles, String fullName) {
		Specification<User> spec = Specification.where(new UserWithRole(roles)).and(new UserWithFullName(fullName));
		
		return userRepository.findAll(spec);
	}

	@Override
	public User getByUsername(@NotBlank String username) {
		return userRepository.findById(username).orElseThrow(NotFoundException::new);
	}

	@Override
	@Validated(OnUserCreate.class)
	public User save(@NotNull @Valid User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	@Validated(OnUserUpdate.class)
	public User update(@NotNull @Valid User user) {
		User dbUser = getByUsername(user.getUsername());

		if (user.getPasswd() != null) {
			dbUser.setPasswd(user.getPasswd());
		}
		if (user.getNombre() != null) {
			dbUser.setNombre(user.getNombre());
		}
		if (user.getDni() != null) {
			dbUser.setDni(user.getDni());
		}
		if (user.getEmail() != null) {
			dbUser.setEmail(user.getEmail());
		}
		if (user.getRole() != null) {
			dbUser.setRole(user.getRole());
		}
		if (user.getIsMonitor() != null) {
			dbUser.setIsMonitor(user.getIsMonitor());
		}

		return userRepository.saveAndFlush(dbUser);
	}

	@Override
	public void deleteByUsername(@NotBlank String username) {
		userRepository.deleteById(username);
	}



}
