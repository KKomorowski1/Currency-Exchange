package pl.alx.szkolenie;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class PobierzKursy {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://api.nbp.pl/api/exchangerates/tables/A?format=xml");
			try (InputStream strumien = url.openStream()){
				
				Files.copy(strumien,  Paths.get("kursy.xml"), StandardCopyOption.REPLACE_EXISTING);
				System.out.println("gotowe");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
