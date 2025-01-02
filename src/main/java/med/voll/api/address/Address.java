package med.voll.api.address;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@AllArgsConstructor
public class Address {
    private String street;
    private String district;
    private String city;
    private String number;
    private String complement;

    public Address(){}
    public Address(AddressDto addressDto) {
         this.street = addressDto.street();
         this.district = addressDto.district();
         this.city = addressDto.city();
         this.number = addressDto.number();
         this.complement = addressDto.complement();
        
    }
}
