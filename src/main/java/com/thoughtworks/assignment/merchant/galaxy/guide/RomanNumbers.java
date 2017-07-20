package com.thoughtworks.assignment.merchant.galaxy.guide;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class RomanNumbers {

	private static Map<String, Float> romanNumbers;
	
	// look up value for all the roman numbers
	public static Map<String, Float> getRomanNumbers() {
        if(romanNumbers == null){
        	romanNumbers  = new HashMap<String, Float>(7);
        	romanNumbers.put( MerchantGuideConstatnts.ROMAN_I , 1f);
        	romanNumbers.put( MerchantGuideConstatnts.ROMAN_V , 5f);
        	romanNumbers.put( MerchantGuideConstatnts.ROMAN_X , 10f);
        	romanNumbers.put( MerchantGuideConstatnts.ROMAN_L , 50f);
        	romanNumbers.put( MerchantGuideConstatnts.ROMAN_C , 100f);
        	romanNumbers.put( MerchantGuideConstatnts.ROMAN_D , 500f);
        	romanNumbers.put( MerchantGuideConstatnts.ROMAN_M , 1000f);
        	romanNumbers = Collections.unmodifiableMap(romanNumbers);
        }
		return romanNumbers;
    }
}
