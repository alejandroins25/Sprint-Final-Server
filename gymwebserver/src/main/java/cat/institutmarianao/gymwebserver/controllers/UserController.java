package cat.institutmarianao.gymwebserver.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cat.institutmarianao.gymwebserver.exception.ForbiddenException;
import cat.institutmarianao.gymwebserver.model.User;
import cat.institutmarianao.gymwebserver.model.dto.ClienteDto;
import cat.institutmarianao.gymwebserver.model.dto.MonitorDto;
import cat.institutmarianao.gymwebserver.model.dto.ResponsableDto;
import cat.institutmarianao.gymwebserver.model.dto.UserDto;
import cat.institutmarianao.gymwebserver.services.UserService;
import cat.institutmarianao.gymwebserver.validation.groups.OnUserCreate;
import cat.institutmarianao.gymwebserver.validation.groups.OnUserUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
/* Swagger */
@Tag(name = "User", description = "UserController API")
/**/
@RestController
@RequestMapping("/users")
@Validated
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Operation(summary = "Authenticate a user")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
            @Content(mediaType = "application/json", examples = {
                    @ExampleObject(value = "{\"username\":\"string\",\"password\":\"string\"}") }) })
    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(oneOf = {
            ResponsableDto.class, MonitorDto.class, ClienteDto.class }, discriminatorProperty = "role")) }, description = "User authenticated ok")
    @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json") }, description = "Resource not found")
    @PostMapping("/authenticate")
    public UserDto authenticate(@RequestBody Map<String, String> usernamePassword) {

        User user = userService.getByUsername(usernamePassword.get("username"));

        if (user != null && passwordEncoder.matches(usernamePassword.get("password"), user.getPasswd())) {
            return conversionService.convert(user, UserDto.class);
        }

        throw new ForbiddenException();
    }

    @Operation(summary = "Find all users")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(oneOf = {
            		ResponsableDto.class, MonitorDto.class, ClienteDto.class }, discriminatorProperty = "role"))) }, description = "Users retrieved ok")
    @GetMapping(value = "/find/all")
    public List<UserDto> findAll(@RequestParam(value = "roles", required = false) User.Role[] roles,
                                 @RequestParam(value = "name", required = false) String name) {

        List<User> users = userService.findAll(roles, name);

        List<UserDto> usersDto = new ArrayList<>(users.size());
        for (User user : users) {
            UserDto userDto = conversionService.convert(user, UserDto.class);
            usersDto.add(userDto);
        }

        return usersDto;
    }
    


    @Operation(summary = "Get user by username")
    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(oneOf = {
    		ResponsableDto.class, MonitorDto.class, ClienteDto.class }, discriminatorProperty = "role")) }, description = "User retrieved ok")
    @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json") }, description = "Resource not found")
    @GetMapping("/get/by/username/{username}")
    public UserDto findByUsername(@PathVariable("username") @NotBlank String username) {

        User user = userService.getByUsername(username);
        return conversionService.convert(user, UserDto.class);
    }
    
    @Operation(summary = "Get user by id")
    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(oneOf = {
    		ResponsableDto.class, MonitorDto.class, ClienteDto.class }, discriminatorProperty = "role")) }, description = "User retrieved ok")
    @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json") }, description = "Resource not found")
    @GetMapping("/get/by/id/{id}")
    public UserDto findById(@PathVariable("id") @NotNull Long id) {

        User user = userService.getById(id);
        return conversionService.convert(user, UserDto.class);
    }

    @Operation(summary = "Save a user")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
            @Content(mediaType = "application/json", schema = @Schema(oneOf = { UserDto.class }, discriminatorProperty = "role")) })
    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(oneOf = {
    		ResponsableDto.class, MonitorDto.class, ClienteDto.class }, discriminatorProperty = "role")) }, description = "User saved ok")
    @PostMapping("/save")
    @Validated(OnUserCreate.class)
    public UserDto save(@RequestBody @Valid UserDto userDto) {
    	User user = conversionService.convert(userDto, User.class);
        User savedUser = userService.save(user);
        return conversionService.convert(savedUser, UserDto.class);
    }
   

    @Operation(summary = "Update a user")
    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(oneOf = {
            ResponsableDto.class, MonitorDto.class, ClienteDto.class }, discriminatorProperty = "role")) }, description = "User updated ok")
    @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json") }, description = "Resource not found")
    @PutMapping("/update/{id}")
    @Validated(OnUserUpdate.class)
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        try {
            System.out.println("Received update request for ID: " + id);

            // Encuentra el usuario existente
            Optional<User> existingUserOptional = userService.findById(id);
            if (!existingUserOptional.isPresent()) {
                System.out.println("User not found for ID: " + id);
                return ResponseEntity.notFound().build();
            }

            User existingUser = existingUserOptional.get();

            // Actualiza los campos del usuario existente con los datos del UserDto
            existingUser.setUsername(userDto.getUsername());
            existingUser.setPasswd(userDto.getPasswd());
            existingUser.setName(userDto.getName());
            existingUser.setDni(userDto.getDni());
            existingUser.setEmail(userDto.getEmail());
            existingUser.setAge(userDto.getAge());
            existingUser.setRole(userDto.getRole());

            // Guarda el usuario actualizado
            User updatedUser = userService.save(existingUser);
            UserDto updatedUserDto = conversionService.convert(updatedUser, UserDto.class);

            return ResponseEntity.ok(updatedUserDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	
    @Operation(summary = "Delete a user by id")
    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json") }, description = "User deleted ok")
    @DeleteMapping("/delete/by/id/{id}")
    public void deleteById(@PathVariable("id") @NotNull Long id) {
        userService.deleteById(id);
    }
    
    @Operation(summary = "Delete a user by username")
    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json") }, description = "User deleted ok")
    @DeleteMapping("/delete/by/username/{username}")
    public void deleteByUsername(@PathVariable("username") @NotBlank String username) {
        userService.deleteByUsername(username);
    }
    
	private User convertAndEncodePassword(User user) {

		String rawPassword = user.getPasswd();
		if (rawPassword != null) {
			user.setPasswd(passwordEncoder.encode(rawPassword));
		}
		return user;
	}

}
