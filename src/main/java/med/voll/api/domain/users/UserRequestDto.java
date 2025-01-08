package med.voll.api.domain.users;

import jakarta.validation.constraints.NotNull;

public record UserRequestDto(
        @NotNull
        String username,

        @NotNull
        String password) {
}
