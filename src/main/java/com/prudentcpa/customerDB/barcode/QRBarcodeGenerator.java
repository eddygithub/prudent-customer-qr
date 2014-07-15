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

public class QRBarcodeGenerator implements BarCodeGenerator{
	
	//use with byte array
	final static String byteArrayCharset = "ISO-8859-1";
	final static String charset = "UTF-8"; // or "ISO-8859-1";
	
	@Override
	public void createImageBarCode(String data, String filePath) throws IOException, WriterException{
		Map<EncodeHintType, Object> hintMap = new HashMap<EncodeHintType, Object>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		createQRCode(data, filePath, charset, hintMap, 200, 200);
	}
	
	private void createQRCode(String input, String filePath,
			String charset, Map<EncodeHintType, ?> hintMap, int qrCodeheight, int qrCodewidth)
			throws WriterException, IOException {
		
		BitMatrix matrix = new MultiFormatWriter().encode(input,
				BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
		MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
				.lastIndexOf('.') + 1), new File(filePath));
	}
	
	@Override
	public String readCodeContent(String filePath) throws FileNotFoundException, IOException, NotFoundException {
		Map<DecodeHintType, String> decodeHintMap = new HashMap<DecodeHintType, String>();
		decodeHintMap.put(DecodeHintType.CHARACTER_SET, byteArrayCharset);
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(
						ImageIO.read(new FileInputStream(filePath)))));
		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, decodeHintMap);
		return qrCodeResult.getText();
	}
}
