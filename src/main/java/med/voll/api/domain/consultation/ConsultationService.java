package med.voll.api.domain.consultation;

import med.voll.api.domain.doctor.DoctorRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.consultation.validations.CancellationBefore24hValidation;
import med.voll.api.domain.consultation.validations.ConsultationValidator;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.infra.errors.NotFoundException;

@Service
public class ConsultationService {
  @Autowired
  ConsultationRepository consultationRepository;

  @Autowired
  DoctorRepository doctorRepository;

  @Autowired
  PatientRepository patientRepository;

  @Autowired
  private List<ConsultationValidator> validators;

  @Autowired
  private CancellationBefore24hValidation cancellationValidation;

  public AppointmentResponseDto bookingConsultation(AppointmentRequestDto appointment) {
    if (appointment.idDoctor() != null && !doctorRepository.existsById(appointment.idDoctor())) {
      throw new NotFoundException("Doctor not found");
    }

    if (!patientRepository.existsById(appointment.idPatient())) {
      throw new NotFoundException("Patient not found");
    }

    validators.forEach(validator -> validator.validate(appointment));

    var doctor = chooseDoctor(appointment);

    if (doctor == null) {
      throw new NotFoundException("There are not doctors available for this specialty at this time");
    }

    var patient = patientRepository.getReferenceById(appointment.idPatient());
    var date = appointment.date();

    var consultation = new Consultation(doctor, patient, date);
    var newConsultation = consultationRepository.save(consultation);
    return new AppointmentResponseDto(newConsultation);
  }

  private Doctor chooseDoctor(AppointmentRequestDto appointment) {
    if (appointment.idDoctor() != null) {
      return doctorRepository.getReferenceById(appointment.idDoctor());
    }

    if (appointment.specialty() == null) {
      throw new NotFoundException("You most provide a specialty if doctor it's not provided");
    }

    return doctorRepository.findAvailableRandomDoctorBySpecialty(appointment.specialty(),
        appointment.date());
  }

  public CancelAppointmentResponseDto cancelConsultation(CancelAppointmentRequestDto cancellation) {
    if (!consultationRepository.existsById(cancellation.idConsultation())) {
      throw new NotFoundException("Consultation not found");
    }

    var consultation = consultationRepository.getReferenceById(cancellation.idConsultation());

    cancellationValidation.validate(consultation);

    consultation.cancel(cancellation.reason());
    var cancelled = consultationRepository.save(consultation);
    return new CancelAppointmentResponseDto(cancelled);
  }
}
