package br.edu.ifce.mn.ads.usercrud.model.domain;


import br.edu.ifce.mn.ads.usercrud.model.User;
import br.edu.ifce.mn.ads.usercrud.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserService {

    @Autowired
    private UserRepository repository;

    @Retryable(maxRetries = 3)
    public UserResponseDTO update(Long id, UserDomain userDomain) {
        User user = repository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found with id " + id)
        );
        boolean hasOtherUser = repository.existsByIdNotAndUsername(userDomain.id(), userDomain.username());
        if (hasOtherUser) {
            throw new RuntimeException("User with username " + userDomain.username() + " already exists");
        }
        user.setUsername(userDomain.username());
        User userSaved = repository.save(user);
        return new UserResponseDTO(
                userSaved.getId(),
                "Usuário atualizado com sucesso"
        );
    }
}
