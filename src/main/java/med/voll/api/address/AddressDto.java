package med.voll.api.address;

import jakarta.validation.constraints.NotBlank;

public record AddressDto(
        @NotBlank
        String street,

        @NotBlank
        String district,

        @NotBlank
        String city,
        String number,
        String complement
) {}
