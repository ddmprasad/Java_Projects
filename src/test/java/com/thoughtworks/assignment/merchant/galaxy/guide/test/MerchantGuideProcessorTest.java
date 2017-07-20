/**
 * 
 */
package com.thoughtworks.assignment.merchant.galaxy.guide.test;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.thoughtworks.assignment.merchant.galaxy.guide.MerchantGuideProcessor;

/**
 * @author prasad
 *
 */
public class MerchantGuideProcessorTest {

	/**
	 * Test method for {@link com.thoughtworks.assignment.merchant.galaxy.guide.MerchantGuideProcessor#processMerchantsInput(java.io.File)}.
	 */
	@Test
	public void testProcessMerchantsInput() {
		MerchantGuideProcessor merchantGuideProcessor = new MerchantGuideProcessor();
		File file = new File("src/test/resources/InputText.txt");
		List<String> lst = merchantGuideProcessor.processMerchantsInput(file);
		Assert.assertNotNull(lst);
	}

}
