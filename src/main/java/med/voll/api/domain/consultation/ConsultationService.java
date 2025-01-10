package med.voll.api.domain.consultation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
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

  public void bookingConsultation(AppointmentRequestDto appointment) {
    if (appointment.idDoctor() != null && !doctorRepository.existsById(appointment.idDoctor())) {
      throw new NotFoundException("Doctor not found");
    }
    if (!patientRepository.existsById(appointment.idPatient())) {
      throw new NotFoundException("Patient not found");
    }

    var doctor = chooseDoctor(appointment);
    var patient = patientRepository.getReferenceById(appointment.idPatient());
    var date = appointment.date();

    var consultation = new Consultation(doctor, patient, date);
    consultationRepository.save(consultation);
  }

  public Doctor chooseDoctor(AppointmentRequestDto appointment){
    if(appointment.idDoctor() != null){
      return doctorRepository.getReferenceById(appointment.idDoctor());
    }

    if(appointment.specialty() == null){
      throw new NotFoundException("You most provide a specialty if doctor it's not provided");
    }

    return doctorRepository.findAvailableRandomDoctorBySpecialty(
      appointment.specialty(), 
      appointment.date()
      );
  }

}
