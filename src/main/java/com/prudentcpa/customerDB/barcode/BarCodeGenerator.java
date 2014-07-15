package com.prudentcpa.customerDB.barcode;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;


public interface BarCodeGenerator {

	/**
	 * 
	 * @return the location of the image file
	 */
	void createImageBarCode(String data, String filePath) throws IOException, WriterException;
	
	String readCodeContent(String filePath) throws FileNotFoundException, IOException, NotFoundException;
}
