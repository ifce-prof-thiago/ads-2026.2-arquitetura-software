package br.edu.ifce.mn.ads.usercrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping
    public User criar(@RequestBody User input) {
        return userService.criar(input);
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

    @PutMapping
    public User update(@RequestBody User input) {
        return userService.criar(input);
    }

    @DeleteMapping("{user_id}")
    public void delete(@PathVariable("user_id") Long userId) {
        userRepository.deleteById(userId);
    }

}
