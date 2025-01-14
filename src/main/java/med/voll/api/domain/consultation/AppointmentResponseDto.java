package med.voll.api.domain.consultation;

import java.time.LocalDateTime;

public record AppointmentResponseDto(Long id, Long idDoctor, Long idPatient, LocalDateTime date) {
        public AppointmentResponseDto(Consultation appointment) {
                this(appointment.getId(), appointment.getDoctor().getId(),
                                appointment.getPatient().getId(), appointment.getDate());
        }
}
