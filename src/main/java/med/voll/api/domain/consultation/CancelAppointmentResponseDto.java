package med.voll.api.domain.consultation;

public record CancelAppointmentResponseDto(Long idConsultation, CancelReason reason) {
	public CancelAppointmentResponseDto(Consultation consultation){
		this(consultation.getId(), consultation.getCancelReason());
	}
}