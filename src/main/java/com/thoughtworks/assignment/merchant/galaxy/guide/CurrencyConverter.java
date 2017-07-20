/**
 * 
 */
package com.thoughtworks.assignment.merchant.galaxy.guide;

import java.util.Map;

/**
 * @author prasad
 *
 */
public interface CurrencyConverter {

	 public float convertValue(String[] elementDesc) throws MerchantGuideException;
}
