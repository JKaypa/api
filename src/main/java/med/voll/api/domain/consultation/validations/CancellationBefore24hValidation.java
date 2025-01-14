package med.voll.api.domain.consultation.validations;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import med.voll.api.domain.consultation.Consultation;
import med.voll.api.domain.consultation.ConsultationRepository;
import med.voll.api.infra.errors.BusinessRuleException;

public class CancellationBefore24hValidation {

	@Autowired
	ConsultationRepository consultationRepository;

	public void validate(Consultation consultation){
		var currentTime = LocalDateTime.now();
		var appointmentDate = consultation.getDate();
		var spaceTime = Duration.between(currentTime, appointmentDate).toHours();
		if (spaceTime < 24){
			throw new BusinessRuleException("You can't cancel an appointment with less than 24h before");
		}
	}
}
