package br.edu.ifce.mn.ads.usercrud.model.domain;

public record UserDomain(
        Long id,
        String username,
        String password
) {
    public UserDomain {
        if (username == null || username.length() < 3 || username.length() > 50) {
            throw new RuntimeException("Invalid username");
        }
        if (password == null || password.length() < 8 || password.length() > 15) {
            throw new RuntimeException("Invalid password");
        }
    }

}
