package wroclaw.webrest.interfaces;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import wroclaw.webrest.entity.DocumentEntity;
import wroclaw.webrest.entity.HistoryEntity;
import wroclaw.webrest.service.CurrencyService;

public interface DocumentServiceInterface {
	/**
	 * This method allow save document found by link.
	 * 
	 * @see CurrencyService#saveCurrencyFromDocument(String, DocumentEntity)
	 * @param link
	 * @throws DOMException
	 * @throws MalformedURLException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public DocumentEntity saveDocument(String link, HistoryEntity historyEntity)
			throws DOMException, MalformedURLException, SAXException, IOException, ParserConfigurationException;

	/**
	 * Return date of publish document. e.g: 2017-05-18
	 * 
	 * @param link
	 * @return
	 * @throws DOMException
	 * @throws MalformedURLException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public String getDocumentPublish(String link)
			throws DOMException, MalformedURLException, SAXException, IOException, ParserConfigurationException;

	/**
	 * Return internal code of document, e.g. 095/A/NBP/2017
	 * 
	 * @param link
	 * @return
	 * @throws DOMException
	 * @throws MalformedURLException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public String getDocumentInternalCode(String link)
			throws DOMException, MalformedURLException, SAXException, IOException, ParserConfigurationException;

	/**
	 * This method check that there is document with the same internal code.
	 * </br>
	 * If method return null, that means document can be saved.
	 * 
	 * @see DocumentEntity#getInternalCode()
	 * @see #findByInternalCode(String)
	 * @param link
	 * @return
	 * @throws DOMException
	 * @throws MalformedURLException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public boolean canBeSave(String link)
			throws DOMException, MalformedURLException, SAXException, IOException, ParserConfigurationException;

	/**
	 * Date(String) should be given in <b>yyyy-mm-dd</b> format.
	 * 
	 * @see DocumentEntity
	 * @param date
	 * @return
	 */
	public DocumentEntity findByDate(String date);

	/**
	 * 
	 * @see DocumentEntity#getInternalCode()
	 * @param code
	 * @return {@link DocumentEntity}
	 */
	public DocumentEntity findByInternalCode(String code);
}
