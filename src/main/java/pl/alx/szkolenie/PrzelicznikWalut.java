package pl.alx.szkolenie;

import java.math.BigDecimal;
import java.util.Scanner;

public class PrzelicznikWalut {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//wczytaj kursy walut
		TabelaWalut tw = ObslugaNBP.wczytajBiezaceKursyWalut();
		
		//zapytaj o kod waluty 
		System.out.println("\nProsze podać kod waluty");
		String kod = sc.nextLine().toUpperCase();
		
		Waluta waluta = tw.find(kod);
		
		if(waluta == null) {
			System.out.println("nie ma takiej waluty");
			return;
		}

		//zapytaj o kwotę
		System.out.println("\nProsze podać kwote");
		String kwotaString = sc.nextLine();
		
		BigDecimal kwota = new BigDecimal(kwotaString);
		
		//wyswietl wartosc w pln
		System.out.println("Wartość to " + waluta.przeliczWaluteNaPln(kwota) + "PLN");
		
	}

}
