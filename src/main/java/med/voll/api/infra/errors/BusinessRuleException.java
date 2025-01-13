package med.voll.api.infra.errors;

public class BusinessRuleException extends RuntimeException {

		public BusinessRuleException(String message) {
				super(message);
		}

}
