package med.voll.api.doctor;

import med.voll.api.address.AddressDto;

public record DoctorDetailDto(Long id,
                              String name,
                              String email,
                              String phone,
                              String specialty,
                              String idNumber,
                              AddressDto address) {
    public DoctorDetailDto(Doctor doctor) {
        this(doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getSpecialty().toString(),
                doctor.getIdNumber(),
                new AddressDto(
                        doctor.getAddress().getCity(),
                        doctor.getAddress().getDistrict(),
                        doctor.getAddress().getStreet(),
                        doctor.getAddress().getNumber(),
                        doctor.getAddress().getComplement()));
    }
}
