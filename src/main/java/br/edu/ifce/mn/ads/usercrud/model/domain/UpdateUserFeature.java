package br.edu.ifce.mn.ads.usercrud.model.domain;


import br.edu.ifce.mn.ads.usercrud.model.User;
import br.edu.ifce.mn.ads.usercrud.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserFeature {

    @Autowired
    private UserRepository repository;

    @Retryable(maxRetries = 3)
    public UpdateUserResponseDTO update(Long id, UpdateUserRequestDTO request) {
        User user = repository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found with id " + id)
        );
        UserDomain userDomain = request.toUserDomain(user.getPassword());
        boolean hasOtherUser = repository.existsByIdNotAndUsername(id, userDomain.username());
        if (hasOtherUser) {
            throw new RuntimeException("User with username " + userDomain.username() + " already exists");
        }
        user.setUsername(userDomain.username());
        User userSaved = repository.save(user);
        return new UpdateUserResponseDTO(
                userSaved.getId()
        );
    }
}
