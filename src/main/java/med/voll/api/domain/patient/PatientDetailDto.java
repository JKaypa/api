package med.voll.api.domain.patient;

import med.voll.api.domain.address.AddressDto;

public record PatientDetailDto(Long id,
                               String name,
                               String email,
                               String phone,
                               String idNumber,
                               AddressDto address) {
    public PatientDetailDto(Patient patient) {
        this(patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getIdNumber(),
                new AddressDto(
                        patient.getAddress().getCity(),
                        patient.getAddress().getDistrict(),
                        patient.getAddress().getStreet(),
                        patient.getAddress().getNumber(),
                        patient.getAddress().getComplement()
                )
        );
    }
}
