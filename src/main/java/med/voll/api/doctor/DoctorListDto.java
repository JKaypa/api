package med.voll.api.doctor;

public record DoctorListDto(String name, String email, String specialty, String idNumber) {
    public DoctorListDto(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getSpecialty().toString(), doctor.getIdNumber());
    }
}
