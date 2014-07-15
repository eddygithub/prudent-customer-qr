package com.prudentcpa.customerDB.scan;

import java.io.File;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

public class WebCamMain {

	public static void main(String[] args) throws Exception{
		Webcam webcam = Webcam.getDefault();
		webcam.open();
		ImageIO.write(webcam.getImage(), "PNG", new File("hello-world.png"));
	}

}
