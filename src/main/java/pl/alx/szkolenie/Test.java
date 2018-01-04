package pl.alx.szkolenie;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.w3c.dom.Document;

public class Test {

	public static void main(String[] args) {
		// testujemy Waluta
		Waluta waluta = new Waluta("BitCoin", "BTC",BigDecimal.valueOf(29794.97));
		
		System.out.println(waluta);
		
		System.out.println("Wartość 23 szt: " + waluta.przeliczWaluteNaPln(BigDecimal.valueOf(23)));
		System.out.println("Wartość 100PLN: " + waluta.przeliczPlnNaWalute(BigDecimal.valueOf(100)));
	
	
		//testujemy TabelaWalut
		TabelaWalut tw = new TabelaWalut("A", "12/50", LocalDate.now());
		System.out.println(tw);
		TabelaWalut t2 = new TabelaWalut();
		System.out.println(t2);
		
		System.out.println(tw.getAllRates());
		tw.addCurrency(waluta);
		tw.addCurrency(new Waluta("DogeCoin","DGC",BigDecimal.valueOf(3456.78)));
		
		System.out.println(tw.getAllRates());
		
		//testujemy ObslugaNBP
		TabelaWalut tw3 = ObslugaNBP.wczytajBiezaceKursyWalut();
		System.out.println(tw3.getAllRates());
		
	}

}
