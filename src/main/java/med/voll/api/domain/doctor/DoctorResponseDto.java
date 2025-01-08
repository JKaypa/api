package med.voll.api.doctor;

public record DoctorResponseDto(Long id, String name, String email, String specialty, String idNumber) {
    public DoctorResponseDto(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getSpecialty().toString(), doctor.getIdNumber());
    }
}
