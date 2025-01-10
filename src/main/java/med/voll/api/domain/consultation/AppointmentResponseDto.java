package med.voll.api.domain.consultation;

import java.time.LocalDateTime;

public record AppointmentResponseDto(
                Long id,
                Long idDoctor,
                Long idPatient,
                LocalDateTime date) {
        public AppointmentResponseDto(AppointmentRequestDto appointment) {
                this(null, appointment.idDoctor(), appointment.idPatient(), appointment.date());
        }
}
