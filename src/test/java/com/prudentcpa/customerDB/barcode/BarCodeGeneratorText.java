package com.prudentcpa.customerDB.barcode;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={BarCodeGeneratorApp.class})
public class BarCodeGeneratorText {
	
	@Autowired
	BarCodeGenerator qrCodeGenerator;
	
	@Test
	public void testBarCodeGeneration() throws Exception{
		String user_home = System.getProperty("user.home");
		String filePath = user_home.concat("/Desktop/testQR.png");
		
		qrCodeGenerator.createImageBarCode("Test QR Generation", filePath);
		
		String result = qrCodeGenerator.readCodeContent(filePath);
		assertEquals("Data retrieved should be the same as the data stored", "Test QR Generation", result);
	}
}
