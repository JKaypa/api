package med.voll.api.doctor;

import med.voll.api.address.AddressDto;

public record UpdateDoctorDto(String name, String idNumber, AddressDto address) {
}
