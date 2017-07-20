package com.thoughtworks.assignment.merchant.galaxy.guide;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author prasad
 *
 */
public class MerchantGuideProcessor {
	
	private static final Logger log = Logger.getLogger(MerchantGuideProcessor.class);
	
	public List<String> processMerchantsInput(File file) {
	    BufferedReader b = null;
	    //create input parser 
	    InputParser parser = null;
	    // create output parser
	    OutputParser oParser = null;
		try {
			b = getFileData(file);
			parser = new InputParser(b);
			oParser = new OutputParser(parser, b);
		    //build Roman values lookup from input
		    parser.buildRomanLookupHolder();
		    //build metal value look up from deriving inputs
		    parser.builMetalLookupValues();
		    //process and get output for requested input
		    oParser.processOutput();
		    //print output
		    for (String str : oParser.getOutputList()) {
				log.info(str);
			}
		}catch (MerchantGuideException e) {
			log.error("Error in processMerchantsInput : " + e.getMessage());
		} 
		return oParser.getOutputList();
	}
	
	private BufferedReader getFileData(File file) throws MerchantGuideException{
		try{
			return new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			throw new MerchantGuideException("Error in loading input File data");
		}
	}
}
