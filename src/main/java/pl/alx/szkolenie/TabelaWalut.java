package pl.alx.szkolenie;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class TabelaWalut {
	private String tableName;
	private String tableNo;
	private LocalDate date;
	private Map<String, Waluta> rates = new TreeMap<>();
	
	
	public TabelaWalut() {
		super();
		this.tableName = null;
		this.tableNo = null;
		this.date = null;
	}


	public TabelaWalut(String tableName, String tableNo, LocalDate date) {
		super();
		this.tableName = tableName;
		this.tableNo = tableNo;
		this.date = date;
	}


	public String getTableName() {
		return tableName;
	}


	public String getTableNo() {
		return tableNo;
	}


	public LocalDate getDate() {
		return date;
	}
	
	@Override
	public String toString() {
		return "Tabela " + tableName + " z dnia " + date;
	}
	
	//wrzucanie do mapy kolejnych elementow
	public void addCurrency(Waluta waluta) {
		rates.put(waluta.getCode(), waluta);
	}
	
	public Collection<Waluta> getAllRates() {
		return rates.values();
	}
	
	
	public Waluta find(String kod) {
		return rates.get(kod);
	}
}
