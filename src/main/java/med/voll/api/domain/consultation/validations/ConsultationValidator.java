package med.voll.api.domain.consultation.validations;

import med.voll.api.domain.consultation.AppointmentRequestDto;

public interface ConsultationValidator {
	void validate(AppointmentRequestDto appointment);
}
