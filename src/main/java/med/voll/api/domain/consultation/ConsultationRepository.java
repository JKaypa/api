package med.voll.api.domain.consultation;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;



public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

  boolean   existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime openingHours,
      LocalDateTime closingHours);

  boolean existsByDoctorIdAndDate(Long idDoctor, LocalDateTime date);
}
