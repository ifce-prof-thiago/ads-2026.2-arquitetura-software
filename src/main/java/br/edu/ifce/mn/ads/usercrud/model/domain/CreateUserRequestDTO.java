package br.edu.ifce.mn.ads.usercrud.model.domain;

public record CreateUserRequestDTO(
        String username,
        String password
) {

    public UserDomain toUserDomain() {
        return new UserDomain(
                null,
                username(),
                password()
        );
    }

}
