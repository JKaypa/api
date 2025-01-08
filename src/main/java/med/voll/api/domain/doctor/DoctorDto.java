package med.voll.api.domain.doctor;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.AddressDto;


public record DoctorDto(
        @NotBlank
        String name,
    
        @NotBlank
        @Email
        String email,

        @NotBlank
        String phone,

        @NotBlank
        @Pattern(regexp = "\\d{10}")
        @JsonAlias("id_number") String idNumber,

        @NotNull
        Specialty specialty,

        @Valid
        AddressDto address
) {}
