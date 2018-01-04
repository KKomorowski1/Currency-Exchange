package pl.alx.szkolenie;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Waluta {
	private String currency;
	private String code;
	private BigDecimal mid;
	
	//potrzebne, żeby klasa mogła być Java Bean
	//(technologie JSP, JAXB)
	public Waluta() {
		super();
		this.currency = null;
		this.code = null;
		this.mid = null;
	}


	public Waluta(String currency, String code, BigDecimal mid) {
		super();
		this.currency = currency;
		this.code = code;
		this.mid = mid;
	}


	public String getCurrency() {
		return currency;
	}


	public String getCode() {
		return code;
	}


	public BigDecimal getMid() {
		return mid;
	}
	
	@Override
	public String toString() {
		return this.currency + " " + this.mid;
	}
	
	public BigDecimal przeliczWaluteNaPln(BigDecimal kwota) {
		return kwota.multiply(mid).setScale(2, RoundingMode.HALF_UP);
	}
	
	public BigDecimal przeliczPlnNaWalute(BigDecimal kwota) {
		return kwota.divide(mid,2,RoundingMode.HALF_UP);
	}
	
}
