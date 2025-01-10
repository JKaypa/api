package med.voll.api.domain.consultation;

import jakarta.validation.constraints.NotNull;

public record CancelAppointmentRequestDto(
	@NotNull
	Long idConsultation,

	@NotNull
	CancelReason reason
) {

}
