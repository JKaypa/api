package med.voll.api.domain.consultation.validations;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import med.voll.api.domain.consultation.AppointmentRequestDto;
import med.voll.api.infra.errors.BusinessRuleException;

@Component
public class OutOfTimeValidation implements ConsultationValidator{

	public void validate(AppointmentRequestDto appointment) {
		var date = appointment.date();
		var isSunday = date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
		var isTimeBeforeOpening = date.getHour() < 7;
		var isTimeAfterClosing = date.getHour() > 18;

		if(isSunday || isTimeBeforeOpening || isTimeAfterClosing){
			throw new BusinessRuleException("Consultation out of time");
		}

	}

}
