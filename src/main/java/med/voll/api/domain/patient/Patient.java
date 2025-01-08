package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.address.Address;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    @Column(name = "id_number")
    private String idNumber;
    @Embedded
    private Address address;
    private Boolean active;

    public Patient(PatientDto patientDto) {
        this.name = patientDto.name();
        this.email = patientDto.email();
        this.phone = patientDto.phone();
        this.idNumber = patientDto.idNumber();
        this.address = new Address(patientDto.address());
        this.active = true;
    }


    public void update(UpdatePatientDto updatePatientDto) {
        if(updatePatientDto.name() != null){
            this.name = updatePatientDto.name();
        }
        if(updatePatientDto.idNumber() != null){
            this.idNumber = updatePatientDto.idNumber();
        }
        if(updatePatientDto.address() != null){
            this.address = address.update(updatePatientDto.address());
        }
    }

    public void logicDelete() {
        this.active = false;
    }
}

