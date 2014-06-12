package com.prudentcpa.customerDB.barcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRBarcodeGenerator {
	
	//use with byte array
	final static String byteArrayCharset = "ISO-8859-1";
	
	//TODO externalize these properties
	public void init(){
		String filePath = "/Users/zhue/Desktop/QRCode.png";
		String charset = "UTF-8"; // or "ISO-8859-1"
		Map<EncodeHintType, Object> hintMap = new HashMap<EncodeHintType, Object>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

	}
	
	public static void main(String[] args) throws WriterException, IOException,
			NotFoundException {

		String input = "Chakravarti is a trouble marker!";
		
		String filePath = "/Users/zhue/Desktop/QRCode.png";
		String charset = "UTF-8"; // or "ISO-8859-1"
		Map<EncodeHintType, Object> hintMap = new HashMap<EncodeHintType, Object>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

		createQRCode(input, filePath, charset, hintMap, 200, 200);
		System.out.println("QR Code image created successfully!");

		Map<DecodeHintType, String> decodeHintMap = new HashMap<DecodeHintType, String>();
		decodeHintMap.put(DecodeHintType.CHARACTER_SET, byteArrayCharset);

	//	System.out.println("Data read from QR Code: " + readQRCode(filePath, charset, decodeHintMap));

	}

	public static void createQRCode(String input, String filePath,
			String charset, Map<EncodeHintType, ?> hintMap, int qrCodeheight, int qrCodewidth)
			throws WriterException, IOException {
		
		BitMatrix matrix = new MultiFormatWriter().encode(input,
				BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
		MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
				.lastIndexOf('.') + 1), new File(filePath));
	}

	public static Object readQRCode(String filePath, String charset, Map<DecodeHintType, ?> hintMap)
			throws FileNotFoundException, IOException, NotFoundException {
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(
						ImageIO.read(new FileInputStream(filePath)))));
		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, hintMap);
		
		return qrCodeResult.getText();
	}

}

class Customer implements Serializable{

	private static final long serialVersionUID = -3984652533951911652L;
	private String firstName;
	private String lastName;
	
	public Customer(String fn, String ln) {
		firstName = fn;
		lastName = ln;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	@Override
	public String toString() {
		return String.format("\nCustomer#\n\t First Name: %s \n\t Last Name: %s", firstName, lastName);
	}
}
