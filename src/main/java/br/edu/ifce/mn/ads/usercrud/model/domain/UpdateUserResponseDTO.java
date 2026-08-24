package br.edu.ifce.mn.ads.usercrud.model.domain;

public record UpdateUserResponseDTO(
        Long id
) {

    public String getMessage() {
        return "Usuário atualizado com sucesso";
    }
}
