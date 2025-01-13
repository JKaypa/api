package med.voll.api.domain.consultation.validations;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.consultation.AppointmentRequestDto;
import med.voll.api.infra.errors.BusinessRuleException;

@Component
public class AnticipationScheduleValidation implements ConsultationValidator{

	public void validate(AppointmentRequestDto appointment){
		var date = appointment.date();
		var currentTime = LocalDateTime.now();
		var spaceTime = Duration.between(currentTime, date).toMinutes();

		if(spaceTime < 30){
			throw new BusinessRuleException("Consultation must be scheduled at least 30 minutes in advance");
		}
	}

}
