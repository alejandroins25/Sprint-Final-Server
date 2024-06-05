package cat.institutmarianao.gymwebserver.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.gymwebserver.exception.NotFoundException;
import cat.institutmarianao.gymwebserver.model.Clase;
import cat.institutmarianao.gymwebserver.model.User;
import cat.institutmarianao.gymwebserver.model.User.Role;
import cat.institutmarianao.gymwebserver.repositories.ClaseRepository;
import cat.institutmarianao.gymwebserver.repositories.UserRepository;
import cat.institutmarianao.gymwebserver.services.UserService;
import cat.institutmarianao.gymwebserver.specifications.UserWithDni;
import cat.institutmarianao.gymwebserver.specifications.UserWithRole;
import cat.institutmarianao.gymwebserver.validation.groups.OnUserCreate;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private ClaseRepository claseRepository;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;

	@Override
	public List<User> findAll(Role[] roles, String dni) {
		Specification<User> spec = Specification.where(new UserWithRole(roles)).and(new UserWithDni(dni));
		
		return userRepository.findAll(spec);
	}

	@Override
	public User getById(@NotNull Long id) {
		return userRepository.findById(id).orElseThrow(NotFoundException::new);
	}
	
	@Override
	public User getByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(NotFoundException::new);
	}

	@Override
	@Transactional
	@Validated(OnUserCreate.class)
	public User save(@NotNull @Valid User user) {
		user.setPasswd(passwordEncoder.encode(user.getPasswd()));
		return userRepository.saveAndFlush(user);
	}
	
	@Override
    @Transactional
	public Optional<User> findById(@NotNull @Valid Long id) {
        return userRepository.findById(id);
    }
	
	@Override
    @Transactional
    public Optional<User> updateUser(@NotNull @Valid User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }

        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            User updatedUser = userRepository.save(user);
            return Optional.of(updatedUser);
        } else {
            return Optional.empty();
        }
    }

	@Override
	@Transactional
	public void deleteById(@NotNull Long id) {
		User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        // Establecer el valor del monitor a null en las clases que lo referencian
        List<Clase> classes = claseRepository.findByMonitor(user);
        for (Clase clase : classes) {
            clase.setMonitor(null);
            claseRepository.save(clase);
        }
		userRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void deleteByUsername(@NotBlank String username) {	
		User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException());
        // Establecer el valor del monitor a null en las clases que lo referencian
        List<Clase> classes = claseRepository.findByMonitor(user);
        for (Clase clase : classes) {
            clase.setMonitor(null);
            claseRepository.save(clase);
        }
        // Ahora eliminar el usuario
        userRepository.deleteByUsername(username);
    }

}
