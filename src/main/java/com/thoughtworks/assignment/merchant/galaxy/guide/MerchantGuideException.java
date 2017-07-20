/**
 * 
 */
package com.thoughtworks.assignment.merchant.galaxy.guide;

/**
 * @author prasad
 *
 */
public class MerchantGuideException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public MerchantGuideException() {
	}

	/**
	 * @param message
	 */
	public MerchantGuideException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public MerchantGuideException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MerchantGuideException(String message, Throwable cause) {
		super(message, cause);
	}

}
