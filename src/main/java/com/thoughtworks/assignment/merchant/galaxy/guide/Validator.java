package com.thoughtworks.assignment.merchant.galaxy.guide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 
 */

/**
 * @author prasad
 * 
 */
public class Validator {
	
	private static final Logger log = Logger.getLogger(Validator.class);
	
	private static List<String> nonRepeatedRomans = new ArrayList<String>(
			Arrays.asList("D", "L", "V"));
	private static List<String> repeatedRomans = new ArrayList<String>(
			Arrays.asList("I", "X", "C", "M"));
	private static Map<String, Integer> repetitionCount = null;

	/**
	 * This method checks the input Roman numerals are valid or not
	 * @param str
	 * @return
	 */
	public static boolean isValidRomanNuerical(String str) {
		log.debug("isValidRomanNuerical checking validity for numeral");
		if (RomanNumbers.getRomanNumbers().get(str) != null)
			return true;
		else
			return false;
	}

	/**
	 * This method validate all the Roman numerals validations repeating  and non-repeating
	 * returns true if all validations are pass
	 * @param input
	 * @return
	 * @throws MerchantGuideException
	 */
	public static boolean validateRomanOccurences(String input) throws MerchantGuideException {
		log.debug("validateRomanOccurences method start");
		boolean result = false;
		if(nonRepeatedRomans.contains(input)){
			log.debug("checking non-repeated numerals occurence validation");
			if(getRepeatationCountMap().get(input) <= MerchantGuideConstatnts.NUMBER_ONE){
				//increase occurrence by 1
				getRepeatationCountMap().put(input, getRepeatationCountMap().get(input) + MerchantGuideConstatnts.NUMBER_ONE);
				result = true;
			}else{
				//if occurrence are more than one for non repeating numerals raise error
				log.error(MerchantGuideConstatnts.ERROR_NON_REPEATING_CHARACTERS);
				throw new MerchantGuideException(MerchantGuideConstatnts.ERROR_NON_REPEATING_CHARACTERS);
			}  
		}else if(repeatedRomans.contains(input)){
			log.debug("checking repeated numerals occurence validation");
			if(getRepeatationCountMap().get(input) == MerchantGuideConstatnts.NUMBER_THREE){
				//if current numeral already occurred three times so raise error
				log.error(MerchantGuideConstatnts.ERROR_REPEATING_CHARACTERS);
				throw new MerchantGuideException(MerchantGuideConstatnts.ERROR_REPEATING_CHARACTERS);
			}else if(getRepeatationCountMap().containsKey(MerchantGuideConstatnts.NUMBER_THREE)){
				Iterator<String> iter = getRepeatationCountMap().keySet().iterator();
				while(iter.hasNext()){
					String romanVal = iter.next();
					if(RomanNumbers.getRomanNumbers().get(romanVal) > RomanNumbers.getRomanNumbers().get(input)){
						//if three successive three repeating numerals are followed by smaller value
						getRepeatationCountMap().put(romanVal, 0);
						getRepeatationCountMap().put(input, getRepeatationCountMap().get(input) + MerchantGuideConstatnts.NUMBER_THREE);
						result = true;
					}else{
						log.error(MerchantGuideConstatnts.ERROR_REPEATING_CHARACTERS_WITH_HIGHER_CHARACTER);
						throw new MerchantGuideException(MerchantGuideConstatnts.ERROR_REPEATING_CHARACTERS_WITH_HIGHER_CHARACTER);
					}
				}
			}else{
				getRepeatationCountMap().put(input, getRepeatationCountMap().get(input) + 1);
				result = true;
			}
		}else{
			log.error("Error in validateRomanOccurences : " + " Error in Input");
		}
		return result;
	}

	/**
	 * This method builds a map to keep the count of occurrence in input number for Roman numerals
	 * @return
	 */
	private static Map<String, Integer> getRepeatationCountMap() {
        if(repetitionCount == null){
        	repetitionCount  = new HashMap<String, Integer>(7);
        	repetitionCount.put( MerchantGuideConstatnts.ROMAN_I , 0);
        	repetitionCount.put( MerchantGuideConstatnts.ROMAN_V , 0);
        	repetitionCount.put( MerchantGuideConstatnts.ROMAN_X , 0);
        	repetitionCount.put( MerchantGuideConstatnts.ROMAN_L , 0);
        	repetitionCount.put( MerchantGuideConstatnts.ROMAN_C , 0);
        	repetitionCount.put( MerchantGuideConstatnts.ROMAN_D , 0);
        	repetitionCount.put( MerchantGuideConstatnts.ROMAN_M , 0);
        }
		return repetitionCount;
    }
	
	/**
	 * This method sets all the repetition count for Roman numerals to zero
	 */
	public static void clearRepeatationCountMap() {
        repetitionCount = null;
    }
}
