package services;

public class InvalidInputException extends Exception {
	
	private String errorDetails;
	
	public InvalidInputException(String raison, String errorDetails){
		super(raison);
		this.errorDetails = errorDetails;
	}

	public String getFaultsInfos() {
		return errorDetails;
	}

	
}
