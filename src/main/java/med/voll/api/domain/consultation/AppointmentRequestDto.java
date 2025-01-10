package med.voll.api.domain.consultation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.doctor.Specialty;

import java.time.LocalDateTime;

public record AppointmentRequestDto(
        Long idDoctor,

        Specialty specialty,

        @NotNull
        Long idPatient,

        @NotNull
        @Future
        LocalDateTime date) {
}
