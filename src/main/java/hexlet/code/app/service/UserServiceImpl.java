package hexlet.code.app.service;

import hexlet.code.app.dto.UserDto;
import hexlet.code.app.model.User;
import hexlet.code.app.repository.UserRepository;
import lombok.AllArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createNewUser(final UserDto userDto) {
        final User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(final Long id, final UserDto userDto) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }
    @Override
    public String getCurrentUserName() {
        return null;
    }

    @Override
    public User getCurrentUser() {
        return userRepository.findByEmail(getCurrentUserName()).get();
    }


}