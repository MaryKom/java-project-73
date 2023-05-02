package hexlet.code.controller;

import hexlet.code.dto.UserDto;
import hexlet.code.model.User;
import hexlet.code.repository.UserRepository;
import hexlet.code.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.Valid;

import java.util.List;

import static hexlet.code.controller.UserController.USER_CONTROLLER_PATH;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping("${base-url}" + USER_CONTROLLER_PATH)
public class UserController {
    public static final String USER_CONTROLLER_PATH = "/users";
    public static final String ID = "/{id}";
    private final UserService userService;
    private final UserRepository userRepository;
    private static final String ONLY_OWNER_BY_ID = """
        @userRepository.findById(#id).get().getEmail() == authentication.getName()
        """;

    @Operation(summary = "Create new user")
    @ApiResponse(responseCode = "201", description = "User created")
    @PostMapping
    @ResponseStatus(CREATED)
    public User registerNewUser(@RequestBody @Valid final UserDto userDto) {
        return userService.createNewUser(userDto);
    }

    @ApiResponses(@ApiResponse(responseCode = "200", content =
        @Content(schema = @Schema(implementation = User.class))))
    @Operation(summary = "Get all users")
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .toList();
    }

    @ApiResponses(@ApiResponse(responseCode = "200"))
    @Operation(summary = "Get user")
    @GetMapping(ID)
    public User getUserById(@PathVariable final Long id) {
        return userRepository.findById(id).get();
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User update"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @Operation(summary = "Update user")
    @PreAuthorize(ONLY_OWNER_BY_ID)
    @PutMapping(ID)
    public User updateUser(@PathVariable final Long id, @RequestBody @Valid final UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User update"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @Operation(summary = "Delete user")
    @PreAuthorize(ONLY_OWNER_BY_ID)
    @DeleteMapping(ID)
    public void deleteUser(@PathVariable final Long id) {
        userRepository.deleteById(id);
    }
}
