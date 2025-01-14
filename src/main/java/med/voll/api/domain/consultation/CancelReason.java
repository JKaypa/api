package med.voll.api.domain.consultation;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CancelReason {
	PATIENT_DESISTED("patient desisted"),
	DOCTOR_DESISTED("doctor desisted"),
	OTHER("other");

	public final String reason;
	CancelReason(String reason){
		this.reason = reason;
	}

	@Override
	public String toString() {
		return reason;
	}

	@JsonCreator
	public static CancelReason fromString(String reason){
		for(CancelReason value : values()){
			if(value.name().equalsIgnoreCase(reason)){
				return value;
			}
		}
		throw new IllegalArgumentException("Enum reason argument is not correct: " + reason);
	}

	
}
