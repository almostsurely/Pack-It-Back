package packitback.util;

/**
 * Wrapper for validation from the XML Checker.
 * 
 * @author James
 */
public class Validation {
	
	//FIELDS
	private boolean validated;
	private String validationText;
	
	//CONSTRUCTOR
	public Validation(boolean validated, String validationText){
		this.validated = validated;
		this.validationText = validationText;
	}

	/**
	 * @return the validated
	 */
	public boolean isValidated() {
		return validated;
	}

	/**
	 * @return the validationText
	 */
	public String getValidationText() {
		return validationText;
	}
	
	//METHODS
	
}
