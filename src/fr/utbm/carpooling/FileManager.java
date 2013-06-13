package fr.utbm.carpooling;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
			int value;
			// On utilise un StringBuffer pour construire la chaîne au fur et à mesure
			StringBuffer lu = new StringBuffer();
			// On lit les caractères les uns après les autres
			while((value = input.read()) != -1) {
				// On écrit dans le fichier le caractère lu
				lu.append((char)value);
			}
			
			if(input != null)
				input.close();
			
			return new String(lu.toString().getBytes(Charset.defaultCharset()), Charset.defaultCharset());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
