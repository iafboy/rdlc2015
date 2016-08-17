package com.expocms.server.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import com.google.common.io.Files;

public final class FileUtils {
	
	private FileUtils() {}
	
	public static boolean writeToFile(String fileName, String s) {
		if(fileName == null || fileName.equals(""))
			return false;
		if(s == null || s.equals(""))
			return false;
		
		File f = new File(fileName);
		if(f.exists() && f.delete() == false)
			return false;
		
		try {
			Files.write(s.getBytes(), f);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public static String readFromFile(String fileName) {
		if(fileName == null || fileName.equals(""))
			return null;
		
		File f = new File(fileName);
		if(f.exists() == false)
			return null;
		
		try {
			return Files.readFirstLine(f, Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
