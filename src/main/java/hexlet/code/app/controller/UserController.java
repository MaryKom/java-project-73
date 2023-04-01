package hexlet.code.app.controller;

import hexlet.code.app.dto.UserDto;
import hexlet.code.app.model.User;
import hexlet.code.app.repository.UserRepository;
import hexlet.code.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

import static hexlet.code.app.controller.UserController.USER_CONTROLLER_PATH;

@RestController
@AllArgsConstructor
@RequestMapping("${base-url}" + USER_CONTROLLER_PATH)
public final class UserController {
    public static final String USER_CONTROLLER_PATH = "/users";
    public static final String ID = "/id";
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping
    public User registerNewUser(@RequestBody @Valid final UserDto userDto) {
        return userService.createNewUser(userDto);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .toList();
    }

    @GetMapping(ID)
    public User getUserById(@PathVariable final long id) {
        return userRepository.findById(id).get();
    }

    @PutMapping(ID)
    public User updateUser(@PathVariable final long id, @RequestBody @Valid final UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping(ID)
    public void deleteUser(@PathVariable final long id) {
        userRepository.deleteById(id);
    }
}
