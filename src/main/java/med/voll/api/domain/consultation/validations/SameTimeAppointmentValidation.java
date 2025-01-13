package med.voll.api.domain.consultation.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consultation.AppointmentRequestDto;
import med.voll.api.domain.consultation.ConsultationRepository;
import med.voll.api.infra.errors.BusinessRuleException;

@Component
public class SameTimeAppointmentValidation implements ConsultationValidator{

	@Autowired
	ConsultationRepository consultationRepository;

	public void validate(AppointmentRequestDto appointment) {
		var hasDoctorAnAppointmentAtTheSameTime = consultationRepository
				.existsByDoctorIdAndDate(appointment.idDoctor(), appointment.date());

		if (hasDoctorAnAppointmentAtTheSameTime) {
			throw new BusinessRuleException("Doctor already has an appointment at the same time");
		}
	}

}
