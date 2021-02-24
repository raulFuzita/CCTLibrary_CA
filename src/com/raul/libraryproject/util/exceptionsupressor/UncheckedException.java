package com.raul.libraryproject.util.exceptionsupressor;

/**
 * <p>
 * Class UncheckedException extends RuntimeException.
 * The new recommendation for Java 9 in terms of Exception
 * has changed a little. Many APIs created before Java 9
 * might not accomplish the new best practice guide.
 * This class intends to minimize the tons of Exceptions
 * added those APIs created before Java 9 best practice
 * guide.
 * Don't forget when this class is used any Exception
 * will become unchecked. Keep that in mind.
 * 
 * To support the decision to build this class an article
 * is recommended. Clean Code, A Handbook of Agile 
 * Software Craftsmanship, Robert C. Martin,
 * Use Unchecked Exceptions, page 106.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */

public class UncheckedException extends RuntimeException {

	/**
	 * Serialization can represent an object in sequence bytes.
	 * Once an object is serialized it can be stored in a file
	 * and then retrieve it by deserializing it.
	 * This ID was auto-generated.
	 */
	private static final long serialVersionUID = -1261448804248930989L;
	
	// Store the exact exception type
	private Throwable trowable = null;
	
	/**
	 * This method returns the exact exception that was thrown.
	 * @return Throwable with the exception type
	 */
	public Throwable getException() {
		return this.trowable;
	}

	/**
	 * This method will suppress an Exception error.
	 * @param e is Exception type.
	 */
	public UncheckedException(Exception e) {
		super(e);
		trowable = e;
	}
	
	/**
	 * It'll pass a message to the RuntimeException.
	 * @param message is a String type.
	 */
	public UncheckedException(String message) {
		super(message);
	}
	
	/**
	 * It'll pass a Throwable cause to the RuntimeException.
	 * @param cause is a Throwable type.
	 */
	public UncheckedException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * It'll pass a Throwable cause and message to the RuntimeException.
	 * @param message is a String type.
	 * @param throwable is a Throwable type.
	 */
	public UncheckedException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
