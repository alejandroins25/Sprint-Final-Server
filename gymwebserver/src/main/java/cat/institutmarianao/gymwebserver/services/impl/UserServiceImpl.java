package cat.institutmarianao.gymwebserver.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.gymwebserver.exception.NotFoundException;
import cat.institutmarianao.gymwebserver.model.User;
import cat.institutmarianao.gymwebserver.model.User.Role;
import cat.institutmarianao.gymwebserver.repositories.UserRepository;
import cat.institutmarianao.gymwebserver.services.UserService;
import cat.institutmarianao.gymwebserver.specifications.UserWithDni;
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
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;

	@Override
	public List<User> findAll(Role[] roles, String dni) {
		Specification<User> spec = Specification.where(new UserWithRole(roles)).and(new UserWithDni(dni));
		
		return userRepository.findAll(spec);
	}

	@Override
	public User getById(@NotBlank Long id) {
		return userRepository.findById(id).orElseThrow(NotFoundException::new);
	}
	
	@Override
	public User getByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(NotFoundException::new);
	}

	@Override
	@Validated(OnUserCreate.class)
	public User save(@NotNull @Valid User user) {
		user.setPasswd(passwordEncoder.encode(user.getPasswd()));
		return userRepository.saveAndFlush(user);
	}

	@Override
	@Validated(OnUserUpdate.class)
	public User update(@NotNull @Valid User user) {
		User dbUser = getById(user.getId());

		if (user.getPasswd() != null) {
			dbUser.setPasswd(user.getPasswd());
		}
		if (user.getName() != null) {
			dbUser.setName(user.getName());
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

		return userRepository.saveAndFlush(dbUser);
	}

	@Override
	public void deleteById(@NotBlank Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public void deleteByUsername(@NotBlank String username) {
		userRepository.deleteByUsername(username);
		
	}

}
