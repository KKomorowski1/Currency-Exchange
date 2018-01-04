package pl.alx.szkolenie;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ObslugaNBP {

	public static TabelaWalut wczytajBiezaceKursyWalut() {
		Document doc = wczytajXmlZAdresu("http://api.nbp.pl/api/exchangerates/tables/A?format=xml");

		return zbudujTabeleZXml(doc);
	}

	private static Document wczytajXmlZAdresu(String adres) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();

			URL url = new URL(adres);
			try (InputStream input = url.openStream()) {
				return db.parse(input);
			}

		} catch (IOException | SAXException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static TabelaWalut zbudujTabeleZXml(Document doc) {
		try {
			XPathFactory xpf = XPathFactory.newInstance();
			XPath xpath = xpf.newXPath();

			// szukanie w drzewie xml wg konkretnej ścieżki dostępu
			// String nazwaTabeli =
			// xpath.evaluate("/ArrayOfExchangeRatesTable/ExchangeTable/Table", doc);

			// szukanie wszędzie tylko po nazwie taga
			String nazwaTabeli = xpath.evaluate("//Table", doc);
			String numerTabeli = xpath.evaluate("//No", doc);
			String dataString = xpath.evaluate("//EffectiveDate", doc);
			LocalDate data = LocalDate.parse(dataString);

			TabelaWalut tw = new TabelaWalut(nazwaTabeli, numerTabeli, data);

			NodeList rates = (NodeList) xpath.evaluate("//Rate", doc, XPathConstants.NODESET);
			// NodeList rates = doc.getElementsByTagName("Rate");

			System.out.println("przed petla" + rates.getLength());
			for (int i = 0; i < rates.getLength(); i++) {

				System.out.print("i=" + i + " getLength=" + rates.getLength() + " ");

				Node rate = rates.item(i);
				System.out.println(rate);
				String kod = xpath.evaluate("Code", rate);
				String nazwa = xpath.evaluate("Currency", rate);
				String kurs = xpath.evaluate("Mid", rate);

				System.out.println(kod + " " + nazwa + " " + kurs);

				Waluta waluta = new Waluta(nazwa, kod, new BigDecimal(kurs));
				tw.addCurrency(waluta);
			}

			return tw;

		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
