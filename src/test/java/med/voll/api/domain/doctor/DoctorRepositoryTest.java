package med.voll.api.domain.doctor;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManager;
import med.voll.api.domain.address.AddressDto;
import med.voll.api.domain.consultation.Consultation;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientDto;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	private EntityManager entityManager;

  @Test
	@DisplayName("Should return null when a doctor exists but is not available in that date")
  void testFindAvailableRandomDoctorBySpecialtyScenario1() {
		var nexMondayAt10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
		var doctor = saveDoctor("Doctor1", "doctor1@email.com", "123456789", Specialty.CARDIOLOGY);
		var patient = savePatient("Patient1", "patient1@email.com", "987654321");
		scheduleAppointment(doctor, patient, nexMondayAt10);

		var availableDoctor = doctorRepository.findAvailableRandomDoctorBySpecialty(Specialty.CARDIOLOGY, nexMondayAt10);

		assertThat(availableDoctor).isNull();
  }

	@Test
	@DisplayName("Should return a doctor when exists and is available in that date")
  void testFindAvailableRandomDoctorBySpecialtyScenario2() {
		var nexMondayAt10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
		var doctor = saveDoctor("Doctor1", "doctor1@email.com", "123456789", Specialty.CARDIOLOGY);
		
		var availableDoctor = doctorRepository.findAvailableRandomDoctorBySpecialty(Specialty.CARDIOLOGY, nexMondayAt10);

		assertThat(availableDoctor).isEqualTo(doctor);
  }

	private void scheduleAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
		entityManager.persist(new Consultation(doctor, patient, date));
	}

	private Doctor saveDoctor(String name, String email, String idNumber, Specialty specialty) {
		var doctor = new Doctor(doctorData(name, email, idNumber, specialty));
		entityManager.persist(doctor);
		return doctor;
	}

	private Patient savePatient(String name, String email, String idNumber) {
		var patient = new Patient(patientData(name, email, idNumber));
		entityManager.persist(patient);
		return patient;
	}

	private DoctorDto doctorData(String name, String email, String idNumber, Specialty specialty) {
		return new DoctorDto(name, email, "1234564", idNumber, specialty, address());	
	}

	private PatientDto patientData(String name, String email, String idNumber) {
		return new PatientDto(name, email, "1234564", idNumber, address());
	}

	private AddressDto address() {
		return new AddressDto("City Z", "District Y", "Street X", "123", "A");
	}
}
