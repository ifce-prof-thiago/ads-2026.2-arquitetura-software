package br.edu.ifce.mn.ads.usercrud.model.domain.update;

public record UpdateUserResponseDTO(
        Long id
) {

    public String getMessage() {
        return "Usuário atualizado com sucesso";
    }
}
