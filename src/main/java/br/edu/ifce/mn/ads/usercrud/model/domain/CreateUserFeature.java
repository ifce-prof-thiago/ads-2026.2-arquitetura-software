package br.edu.ifce.mn.ads.usercrud.model.domain;


import br.edu.ifce.mn.ads.usercrud.model.User;
import br.edu.ifce.mn.ads.usercrud.model.UserRepository;
import br.edu.ifce.mn.ads.usercrud.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class CreateUserFeature {

    @Autowired
    private UserRepository userRepository;

    @Retryable(maxRetries = 3)
    public CreateUserResponseDTO criar(CreateUserRequestDTO request) {

        UserDomain userDomain = request.toUserDomain();

        if (userRepository.existsByUsername(userDomain.username())) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setPassword(PasswordEncoder.md5(userDomain.password()));
        user.setUsername(userDomain.username());
        User userSaved = userRepository.save(user);
        return new CreateUserResponseDTO(
                userSaved.getId()
        );
    }
}
