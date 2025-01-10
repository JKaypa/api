package med.voll.api.infra.errors;

public class NotFoundException extends RuntimeException{
	public NotFoundException(String message){
		super(message);
	}
}
