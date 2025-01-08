package med.voll.api.domain.doctor;

import med.voll.api.domain.address.AddressDto;

public record UpdateDoctorDto(String name, String idNumber, AddressDto address) {
}
