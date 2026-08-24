package br.edu.ifce.mn.ads.usercrud.model.domain;

public record CreateUserResponseDTO(
        Long id
) {
    public String getMessage() {
        return "Usuário criado com sucesso";
    }
}
