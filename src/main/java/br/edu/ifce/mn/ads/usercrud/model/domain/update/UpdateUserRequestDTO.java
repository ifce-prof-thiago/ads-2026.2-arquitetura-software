package br.edu.ifce.mn.ads.usercrud.model.domain.update;

import br.edu.ifce.mn.ads.usercrud.model.domain.UserDomain;

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
