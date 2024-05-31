package cat.institutmarianao.gymwebserver.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cat.institutmarianao.gymwebserver.model.User;
import jakarta.validation.constraints.NotBlank;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	Optional<User> findByUsername(String username);

	void deleteByUsername(@NotBlank String username);
	
}