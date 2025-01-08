package med.voll.api.domain.doctor;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Specialty {
    ORTHOPEDICS("orthopedics"),
    CARDIOLOGY("cardiology"),
    GYNECOLOGY("gynecology"),
    PEDIATRICS("pediatrics");

    public final String label;
    Specialty(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    @JsonCreator
    public static Specialty fromString(String specialty){
        for(Specialty value : values()){
            if(value.name().equalsIgnoreCase(specialty)){
                return value;
            }
        }
        throw new IllegalArgumentException("Enum specialty argument in not correct: " + specialty);
    }
}
