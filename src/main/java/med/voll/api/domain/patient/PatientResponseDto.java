package med.voll.api.domain.patient;

public record PatientResponseDto(Long id, String name, String email, String idNumber) {
    public PatientResponseDto(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getIdNumber());
    }
}
