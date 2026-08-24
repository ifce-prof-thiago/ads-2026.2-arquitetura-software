package br.edu.ifce.mn.ads.usercrud.model.domain;

public record UpdateUserRequestDTO(
        String username
) {

    public UserDomain toUserDomain(String passwordHashed) {
        return new UserDomain(
                null,
                username(),
                passwordHashed
        );
    }

}
