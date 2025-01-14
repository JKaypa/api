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
            WHERE d.active = TRUE
            AND d.specialty = :specialty
            AND d.id NOT IN (
                SELECT c.doctor.id FROM Consultation c
                WHERE c.date = :date
            )
            ORDER BY RAND()
            LIMIT 1
            """)
    Doctor findAvailableRandomDoctorBySpecialty(Specialty specialty, LocalDateTime date);

    @Query("""
            SELECT d.active
            FROM Doctor d
            WHERE
            d.id = :idDoctor
            """)
    boolean findActiveById(Long idDoctor);
}
