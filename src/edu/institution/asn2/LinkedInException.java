/* Arron WIlcox
 * CIS 2217 - Assignment 2 - LinkedInUser
 * 05-23-2020
 * This class is inherited from the Exception class and creates a new exception called LinkedInException
 */
package edu.institution.asn2;

public class LinkedInException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public LinkedInException() {

		super();

	}

	
	public LinkedInException(String message) {

		super(message);

	}
	
	public LinkedInException(String message,Throwable clause) {

		super(message,clause);

	}

	public LinkedInException(Throwable clause) {

		super(clause);

	}

	protected LinkedInException(String message,Throwable clause,boolean enableSuppresion,boolean writtableStackTrace ) {

		super(message,clause, enableSuppresion, writtableStackTrace);

	}



}



