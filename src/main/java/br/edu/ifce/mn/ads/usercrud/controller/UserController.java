package br.edu.ifce.mn.ads.usercrud.controller;

import br.edu.ifce.mn.ads.usercrud.model.*;
import br.edu.ifce.mn.ads.usercrud.model.domain.*;
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
    private UpdateUserFeature updateUserService;

    @PostMapping
    public CreateUserResponseDTO criar(@RequestBody CreateUserRequestDTO input) {
        return createUserService.criar(input);
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
    public UpdateUserResponseDTO update(@PathVariable("user_id") Long userId, @RequestBody UpdateUserRequestDTO input) {
        return updateUserService.update(userId, input);
    }

    @DeleteMapping("{user_id}")
    public void delete(@PathVariable("user_id") Long userId) {
        userRepository.deleteById(userId);
    }

}
