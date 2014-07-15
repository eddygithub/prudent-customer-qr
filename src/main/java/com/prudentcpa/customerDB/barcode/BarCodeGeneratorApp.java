package com.prudentcpa.customerDB.barcode;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
/**
 * This application is going to generate QRCode based on input text
 * @author zhue
 */
public class BarCodeGeneratorApp {
	
	@Bean
	public BarCodeGenerator barCodeGenerator(){
		return new QRBarcodeGenerator();
	}
}
