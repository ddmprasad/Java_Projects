/**
 * 
 */
package com.thoughtworks.assignment.merchant.galaxy.guide;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author prasad
 *
 */
public class OutputParser {
	
	private static final Logger log = Logger.getLogger(OutputParser.class);

	private InputParser inputParser;
	private BufferedReader bInput;
	private List<String> outputList = new ArrayList<String>();
	
	public OutputParser(InputParser inParser,BufferedReader b) {
		inputParser = inParser;
		bInput = b;
	}
	
	/**
	 * This method parse the input request and convert them into equivalent Roman numerals
	 * Roman numerals are validated and converted into required output
	 * @throws MerchantGuideException
	 */
	public void processOutput() throws MerchantGuideException{
		String readLine = MerchantGuideConstatnts.EMPTY_STRING;
		log.debug("processOutput starts here");
		try {
			while ((readLine = bInput.readLine()) != null && 
					(readLine.toUpperCase().startsWith(MerchantGuideConstatnts.HOW_MANY_STR) 
							|| readLine.toUpperCase().startsWith(MerchantGuideConstatnts.HOW_MUCH_STR))) {
				//how many Credits is glob prok Silver ?
				log.debug("out request for input : "+ readLine);
				String [] mainStrArr = readLine.split(MerchantGuideConstatnts.SPACE);
				//identify the sub array 
				int startPos = 0;
				int endPos = 0;
				//identify the start and end positions
				for (int i = 0; i < mainStrArr.length; i++) {
					if(mainStrArr[i].toLowerCase().equals(MerchantGuideConstatnts.IS)){
						startPos = i+1;
					}
					else if(mainStrArr[i].toLowerCase().equals(MerchantGuideConstatnts.QUESTION_MARK)){
						endPos = i;
					}
				}
				//glob prok Silver
				String [] valueArr = Arrays.copyOfRange(mainStrArr, startPos, endPos);
				//glob prok
				StringBuilder inputValues = new StringBuilder();
				StringBuilder outputValues = new StringBuilder();
				//Silver
				String metal = "";
				//for identifying the valid convertible inputs
				boolean isKnownInput = true;
				float quantity = 0;
				for (int i = 0; i < valueArr.length; i++) {
					String currvalue = MerchantGuideConstatnts.EMPTY_STRING;
					if((currvalue = inputParser.getRomanNumberHolder().get(valueArr[i]))!= null){
						inputValues.append(currvalue+MerchantGuideConstatnts.SPACE);
						//keeping values to show output string
						outputValues.append(valueArr[i]+MerchantGuideConstatnts.SPACE);
					} else if(inputParser.getMetalValues().get(valueArr[i])!= null){
						metal = valueArr[i];
					} else{
						outputList.add(MerchantGuideConstatnts.ERROR_NO_IDEA_OF_INPUT);
						isKnownInput = false;
						break;
					}
				}
				if(isKnownInput){
					quantity = new RomanNumeralConverter().convertValue(inputValues.toString().split(MerchantGuideConstatnts.SPACE));
					if("".equalsIgnoreCase(metal)){
						//built output for non metal name inputs
						outputList.add(outputValues.toString() + MerchantGuideConstatnts.IS
								+ MerchantGuideConstatnts.SPACE + quantity);
					}else{
						//built output for metal name inputs
						outputList.add(outputValues.append(metal).toString() + MerchantGuideConstatnts.SPACE + MerchantGuideConstatnts.IS
								+ MerchantGuideConstatnts.SPACE  + (inputParser.getMetalValues().get(metal) * quantity) 
								+ MerchantGuideConstatnts.SPACE + MerchantGuideConstatnts.CREDITS);
					}
				}
			}
		} catch (IOException e) {
			log.error(MerchantGuideConstatnts.ERROR_IN_READING_INPUT);
			throw new MerchantGuideException(MerchantGuideConstatnts.ERROR_IN_READING_INPUT);
		}
	}

	/**
	 * @return the outputList
	 */
	public List<String> getOutputList() {
		return outputList;
	}

	/**
	 * @param outputList the outputList to set
	 */
	public void setOutputList(List<String> outputList) {
		this.outputList = outputList;
	}
}
