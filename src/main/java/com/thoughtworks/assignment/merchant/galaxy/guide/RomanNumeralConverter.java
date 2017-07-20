package com.thoughtworks.assignment.merchant.galaxy.guide;

import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @author prasad
 *
 */
public class RomanNumeralConverter implements CurrencyConverter{

	private static final Logger log = Logger.getLogger(RomanNumeralConverter.class);
	
	/**
	 * This Method converts input value to decimal value
	 * this also consider all the validations
	 * @param String[]
	 * @param Map<String, String>
	 * @exception Exception
	 */
	@Override
	public float convertValue(String[] elementDesc) throws MerchantGuideException {
		log.debug("convertValue starts here");
		//holds final value
		float total = 0;
		//for checking previous value for subtraction logic
		float prevVal = 0;
		for (int i = 0; i < elementDesc.length; i++) {
			String currentValue = elementDesc[i];
			log.debug("input value is :" + currentValue);
			log.debug("previous value is :" + prevVal);
			//validate first all repeating and non-repeating numerals
			if(Validator.validateRomanOccurences(currentValue)){
				float currVal = RomanNumbers.getRomanNumbers().get(currentValue);
				//"I" can be subtracted from "V" and "X" only. 1 be can subtracted from 5 or 10
				// "X" can be subtracted from "L" and "C" only.  10 be can subtracted from 50 or 100
				// "C" can be subtracted from "D" and "M" only. 100 be can subtracted from 500 or 100
				if(currVal > prevVal && (currVal == 5*prevVal || currVal == 10*prevVal)){
					total = total + (currVal - prevVal) - prevVal;			
				}else{
					// "V", "L", and "D" can never be subtracted. hence getting added
					total = total + currVal;
				}
				//assigning current value to keep copy
				prevVal = currVal;
			}
		}
		//clears all the counter for checking repetitions
		Validator.clearRepeatationCountMap();
		log.debug("convertValue ends here : final total value is " + total);
		return total;
	}
}
