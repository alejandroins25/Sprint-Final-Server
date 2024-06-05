package cat.institutmarianao.gymwebserver.services;

import java.util.List;
import java.util.Optional;

import cat.institutmarianao.gymwebserver.model.User;
import cat.institutmarianao.gymwebserver.model.User.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface UserService {

	List<User> findAll(Role[] roles, String fullName);

	User save(@NotNull @Valid User user);

	//User update(@NotNull @Valid User user);
	
	Optional<User> updateUser(@NotNull @Valid User user);
	
	Optional<User> findById(@NotNull @Valid Long id);

	User getById(@NotNull Long id);

	void deleteById(@NotNull Long id);
	
	void deleteByUsername(@NotBlank String username);

	User getByUsername(@NotBlank String username);
}
