package med.voll.api.domain.patient;

import med.voll.api.domain.address.AddressDto;

public record UpdatePatientDto(String name, String idNumber, AddressDto address) {
}

