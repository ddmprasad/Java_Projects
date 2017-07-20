package com.thoughtworks.assignment.merchant.galaxy.guide;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 
 */

/**
 * @author prasad
 * 
 */
public class InputParser {

	private static final Logger log = Logger.getLogger(InputParser.class);
	
	private static Map<String, String> romanNumberHolder = new HashMap<String, String>();
	private static Map<String, Float> metalValues = new HashMap<String, Float>();

	BufferedReader bInput;

	public InputParser(BufferedReader b) {
		bInput = b;
	}

	/**
	 * This methods reads inputs from buffered reader and builds lookup for text and its equivalent Roman letter
	 * @throws MerchantGuideException 
	 * @exception IOException
	 */
	public void buildRomanLookupHolder() throws MerchantGuideException {
		String readLine = MerchantGuideConstatnts.EMPTY_STRING;
		log.debug("buildRomanLookupHolder start here");
		try {
			while ((readLine = this.bInput.readLine()) != null
					&& !MerchantGuideConstatnts.END_OF_ROMAN_INPUT_STRING_IDENTIFIER.equalsIgnoreCase(readLine)) {
				//glob is I
				//prok is V
				//pish is X
				//tegj is L
				log.debug("inpu is : " +readLine);
				String[] inArr = readLine.split(MerchantGuideConstatnts.SPACE);
				
				if (Validator.isValidRomanNuerical(inArr[2])) {
					//glob : I
					//prok : V
					//pish : X
					//tegj : L
					romanNumberHolder.put(inArr[0], inArr[2]);
				} else {
					log.error(MerchantGuideConstatnts.ERROR_INVALID_ROMAN_INPUT);
					throw new MerchantGuideException(MerchantGuideConstatnts.ERROR_INVALID_ROMAN_INPUT);
				}
				readLine = MerchantGuideConstatnts.EMPTY_STRING;
			}
		} catch (IOException e) {
			log.error(MerchantGuideConstatnts.ERROR_IN_READING_INPUT);
			throw new MerchantGuideException(MerchantGuideConstatnts.ERROR_IN_READING_INPUT);
		}
	}

	/**
	 * This methods reads inputs from buffered reader and builds lookup for metal and its equivalent value
	 * @throws Exception
	 */
	public void builMetalLookupValues() throws MerchantGuideException {
		String readLine = MerchantGuideConstatnts.EMPTY_STRING;
		log.debug("builMetalLookupValues starts here");
		try {
			while ((readLine = this.bInput.readLine()) != null
					&& !MerchantGuideConstatnts.END_OF_METALS_STRING_IDENTIFIER.equalsIgnoreCase(readLine)) {
				//glob glob Silver is 34 Credits
				log.debug("input is : "+ readLine);
				String[] inArr = readLine.split(MerchantGuideConstatnts.SPACE + MerchantGuideConstatnts.IS + MerchantGuideConstatnts.SPACE);
				String[] elementDesc = inArr[0].split(MerchantGuideConstatnts.SPACE);//glob glob Silver
				String elementValue = inArr[1];//34 Credits
				
				String metal = elementDesc[elementDesc.length - 1];//Silver
				//glob glob
				float quantity = calculateElementQuantity(Arrays.copyOfRange(elementDesc, 0, (elementDesc.length-1)));
				float totalValue = Long.parseLong(elementValue.split(MerchantGuideConstatnts.SPACE)[0]);//34
				
				// silver 17.0
				metalValues.put(metal, (totalValue/quantity));
				readLine = MerchantGuideConstatnts.EMPTY_STRING;
			}
		} catch (IOException e) {
			log.error(MerchantGuideConstatnts.ERROR_IN_READING_INPUT);
			throw new MerchantGuideException(MerchantGuideConstatnts.ERROR_IN_READING_INPUT);
		}
	}

	/**
	 * This method returns quantity calculation for metals
	 * @param elementDesc
	 * @return
	 * @throws Exception
	 */
	private float calculateElementQuantity(String[] elementDesc) throws MerchantGuideException {
		log.debug("calculateElementQuantity starts here");
		StringBuilder inputValues = new StringBuilder();
		for (int i = 0; i < elementDesc.length; i++) {
			inputValues.append(romanNumberHolder.get(elementDesc[i]) + MerchantGuideConstatnts.SPACE);
		}
		log.debug("Input value to convert : " + inputValues.toString());
		return new RomanNumeralConverter().convertValue(inputValues.toString().split(MerchantGuideConstatnts.SPACE));
	}

	/**
	 * @return the romanNumberHolder
	 */
	public Map<String, String> getRomanNumberHolder() {
		return romanNumberHolder;
	}

	/**
	 * @param romanNumberHolder the romanNumberHolder to set
	 */
	public void setRomanNumberHolder(Map<String, String> romanNumberHolder) {
		InputParser.romanNumberHolder = romanNumberHolder;
	}

	/**
	 * @return the metalValues
	 */
	public Map<String, Float> getMetalValues() {
		return metalValues;
	}

	/**
	 * @param metalValues the metalValues to set
	 */
	public void setMetalValues(Map<String, Float> metalValues) {
		InputParser.metalValues = metalValues;
	}
	
	

}
