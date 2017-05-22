package wroclaw.webrest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "currency")
public class CurrencyEntity {
	/**
	 * 
	 */
	@Column(name = "currency_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int currencyId;
	/**
	 *
	 */
	@Column(name = "currency_name")
	private String currencyName;
	/**
	 * 
	 */
	@Column
	private String converter;
	/**
	 * 
	 */
	@Column(name = "currency_code")
	private String currencyCode;
	/**
	 * 
	 */
	@Column(name = "exchange_rate")
	private String exchangeRate;
	/**
	 * 
	 */
	@Column
	private String date;
	/**
	 * 
	 */
	@JoinColumn(name = "document_id")
	@ManyToOne
	private DocumentEntity document;
	
	@Column
	private double againstYesterday;

	public int getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	/**
	 * <b>Polish name:</b> nazwa waluty np.: Euro
	 * 
	 * @return
	 */
	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	/**
	 * Check also:
	 * <a href = "https://www.nbp.pl/kursy"><b>http://www.nbp.pl</b></a></br>
	 * <b>Polish name:</b> Przelicznik
	 * 
	 * @return
	 */
	public double getConverter() {
		return Double.parseDouble(converter);
	}

	public void setConverter(String converter) {
		this.converter = converter;
	}

	/**
	 * e.g THB (Thailand) </br>
	 * <b>Polish name:</b> Kod waluty np.: THB (Tajlandia)
	 * 
	 * @return
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	/**
	 * e.g.: 3.7808</br>
	 * <b>Polish name:</b>Kurs średni np.: 3.7808
	 * 
	 * @return
	 */
	public double getExchangeRate() {
		String value = exchangeRate.replace(",", ".");
		return Double.parseDouble(value);
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	/**
	 * Format: yyyy-mm-dd
	 * 
	 * @return
	 */
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Return document from where it come from. </br>
	 * Relation: ManyToOne
	 * 
	 * @see ManyToOne
	 * @see DocumentEntity
	 * @return
	 */
	public DocumentEntity getDocument() {
		return document;
	}

	public void setDocument(DocumentEntity document) {
		this.document = document;
	}

	public double getAgainstYesterday() {
		return againstYesterday;
	}

	public void setAgainstYesterday(double againstYesterday) {
		this.againstYesterday = againstYesterday;
	}

	@Override
	public String toString() {
		return "Informacje o kursie:  " + currencyName + "</br>Id: " + currencyId +  "</br>Converter: " + converter
				+ "</br>Code: " + currencyCode + "</br>Exchange rate: " + exchangeRate + "</br>Date: " + date
				+ "</br> Względem wczoraj: " + againstYesterday
				+ "</br>Document internal code: " + document.getInternalCode() + "</br></br>";
	}
	
}
