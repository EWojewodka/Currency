package wroclaw.webrest.interfaces;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import wroclaw.webrest.entity.CurrencyEntity;
import wroclaw.webrest.entity.DocumentEntity;

public interface CurrencyServiceInterface {
	/**
	 * Return and save all currency(found by "pozycja") from document.
	 * 
	 * @param link
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public List<CurrencyEntity> saveCurrencyFromDocument(String link, DocumentEntity document)
			throws SAXException, IOException, ParserConfigurationException;

	/**
	 * Distribute currency attribute from node
	 * 
	 * @see #saveCurrencyFromDocument(String, DocumentEntity)
	 * @see Node
	 * @param currencyEntity
	 * @param listOfChild
	 * @return
	 */
	public CurrencyEntity distributeAttribute(CurrencyEntity currencyEntity, NodeList listOfChild);

	/**
	 * Return all currency with given name
	 * 
	 * @param name
	 * @return
	 */
	public List<CurrencyEntity> getAllByName(String name);

	/**
	 * Return all currency with given code
	 * 
	 * @see CurrencyEntity#getCurrencyCode()
	 * @param code
	 * @return
	 */
	public List<CurrencyEntity> getAllByCode(String code);

	/**
	 * Return all currency with given document internal code
	 * 
	 * @see CurrencyEntity#getDocument()
	 * @see DocumentEntity#getInternalCode()
	 * @param documentCode
	 * @return
	 */
	public List<CurrencyEntity> getAllByDocument(String documentCode);

	/**
	 * 
	 * @param code
	 * @return
	 */
	public double getAverangeExchange(String code);

	/**
	 * 
	 * @param currency
	 * @return
	 * @throws ParseException
	 */
	public double againstYesterday(CurrencyEntity currency) throws ParseException;

	/**
	 * Return Map<String, String> Key = code Value = name
	 * 
	 * @return
	 */
	public Map<String, String> getCodesWithName();
}
