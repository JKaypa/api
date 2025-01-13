package med.voll.api.domain.consultation.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consultation.AppointmentRequestDto;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.infra.errors.BusinessRuleException;

@Component
public class ActivePatientValidation implements ConsultationValidator {

	@Autowired
	private PatientRepository patientRepository;

	public void validate(AppointmentRequestDto appointment){
		var activePatient = patientRepository.findActiveById(appointment.idPatient());

		if(!activePatient){
			throw new BusinessRuleException("Patient is not active");
		}
	}
}
