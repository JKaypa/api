package med.voll.api.domain.consultation.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consultation.AppointmentRequestDto;
import med.voll.api.domain.consultation.ConsultationRepository;
import med.voll.api.infra.errors.BusinessRuleException;

@Component
public class NoMoreThanOneAppointmentsPatientByDayValidation implements ConsultationValidator{

	@Autowired
	ConsultationRepository consultationRepository;

	public void validate(AppointmentRequestDto appointment) {
		var openingHours = appointment.date().withHour(7);
		var closingHours = appointment.date().withHour(18);
		var hasPatientAnotherAppointment = consultationRepository
				.existsByPatientIdAndDateBetween(appointment.idPatient(), openingHours, closingHours);

		if (hasPatientAnotherAppointment) {
			throw new BusinessRuleException("Patient already has an appointment for this day");
		}
	}

}
