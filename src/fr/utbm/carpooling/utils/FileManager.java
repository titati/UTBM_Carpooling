package fr.utbm.carpooling.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class FileManager {
	
	public static boolean writeFile(String content, FileOutputStream output) {
		
		try {
		  output.write(content.getBytes(Charset.defaultCharset()));
		  if(output != null)
		    output.close();
		} catch (FileNotFoundException e) {
		  e.printStackTrace();
		  return false;
		} catch (IOException e) {
		  e.printStackTrace();
		  return false;
		}
		
		return true;
	}
	
	public static String readFile(FileInputStream input) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(input, Charset.defaultCharset()), 8192);
			String str;
			String value = "";
			
			// Read lines one by one
			while((str = br.readLine()) != null) {
				// We concatenate the line with the previous ones
				value += str;
			}
			
			if(input != null)
				input.close();
			
			return value;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
