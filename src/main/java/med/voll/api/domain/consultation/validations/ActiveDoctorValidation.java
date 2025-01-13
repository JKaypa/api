package med.voll.api.domain.consultation.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consultation.AppointmentRequestDto;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.infra.errors.BusinessRuleException;

@Component
public class ActiveDoctorValidation implements ConsultationValidator {

	@Autowired
	DoctorRepository doctorRepository;

	public void validate(AppointmentRequestDto appointment){
		if(appointment.idDoctor() == null){
			return;
		}

		var isDoctorActive = doctorRepository.findActiveById(appointment.idDoctor());

		if(!isDoctorActive){
			throw new BusinessRuleException("Doctor is not active");
		}
	}

}
