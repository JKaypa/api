package med.voll.api.doctor;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.address.Address;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    @Column(name = "id_number")
    private String idNumber;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;
    private Boolean active;

    public Doctor(DoctorDto doctorDto) {
        this.name = doctorDto.name();
        this.email = doctorDto.email();
        this.phone = doctorDto.phone();
        this.idNumber = doctorDto.idNumber();
        this.specialty = doctorDto.specialty();
        this.address = new Address(doctorDto.address());
        this.active = true;
    }


    public void update(UpdateDoctorDto updateDoctorDto) {
        if(updateDoctorDto.name() != null){
            this.name = updateDoctorDto.name();
        }
        if(updateDoctorDto.idNumber() != null){
            this.idNumber = updateDoctorDto.idNumber();
        }
        if(updateDoctorDto.address() != null){
            this.address = address.update(updateDoctorDto.address());
        }
    }

    public void logicDelete() {
        this.active = false;
    }
}
