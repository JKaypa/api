package med.voll.api.domain.doctor;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable pagination);

    @Query("""
            SELECT d FROM Doctor d
            WHERE d.active = 1
            AND d.specialty = :specialty
            AND d.id NOT IN (
                SELECT c.doctor_id FROM Consultation c
                WHERE d.date = :date
            )
            ORDER BY RAND()
            LIMIT 1
            """)
    Doctor findAvailableRandomDoctorBySpecialty(Specialty specialty, LocalDateTime date);
}
