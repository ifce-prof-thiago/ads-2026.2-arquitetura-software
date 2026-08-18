package br.edu.ifce.mn.ads.usercrud.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Retryable(maxRetries = 3)
    public User criar(User user) {
        validate(user);
        return userRepository.save(user);
    }

    private void validate(User input) {
        if (input.getUsername() == null || input.getUsername().isEmpty() || input.getUsername().length() < 3 || input.getUsername().length() > 50) {
            throw new RuntimeException("Invalid username");
        }

        if (userRepository.existsByUsername(input.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (input.getPassword() == null || input.getPassword().isEmpty() || input.getPassword().length() < 8 || input.getPassword().length() > 15) {
            throw new RuntimeException("Invalid password");
        }

        try {
            input.setPassword(
                    md5(input.getPassword())
            );
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String md5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
        return HexFormat.of().formatHex(digest);
    }

}
