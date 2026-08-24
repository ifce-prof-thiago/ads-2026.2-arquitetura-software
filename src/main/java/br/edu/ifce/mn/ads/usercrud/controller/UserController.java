package br.edu.ifce.mn.ads.usercrud.controller;

import br.edu.ifce.mn.ads.usercrud.model.*;
import br.edu.ifce.mn.ads.usercrud.model.domain.CreateUserFeature;
import br.edu.ifce.mn.ads.usercrud.model.domain.UpdateUserService;
import br.edu.ifce.mn.ads.usercrud.model.domain.UserRequestDTO;
import br.edu.ifce.mn.ads.usercrud.model.domain.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreateUserFeature createUserService;

    @Autowired
    private UpdateUserService updateUserService;

    @PostMapping
    public UserResponseDTO criar(@RequestBody UserRequestDTO input) {
        return createUserService.criar(input.toUserDomain());
    }

    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("{user_id}")
    public User findById(@PathVariable("user_id") Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found!")
        );
    }

    @PutMapping("{user_id}")
    public UserResponseDTO update(@PathVariable("user_id") Long userId, @RequestBody UserRequestDTO input) {
        return updateUserService.update(userId, input.toUserDomain());
    }

    @DeleteMapping("{user_id}")
    public void delete(@PathVariable("user_id") Long userId) {
        userRepository.deleteById(userId);
    }

}
