package med.voll.api.domain.address;

import jakarta.validation.constraints.NotBlank;

public record AddressDto(
        @NotBlank
        String city,

        @NotBlank
        String district,

        @NotBlank
        String street,
        String number,
        String complement
) {}
