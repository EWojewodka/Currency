package wroclaw.webrest.interfaces;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.rometools.fetcher.FetcherException;
import com.rometools.rome.io.FeedException;

import wroclaw.webrest.entity.HistoryEntity;

public interface HistoryServiceInterface {

	/**
	 * 
	 * @param dateOfLastBuild
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws FetcherException
	 * @throws FeedException
	 * @throws IOException
	 * @throws DOMException
	 * @throws MalformedURLException
	 * @throws IllegalArgumentException
	 */
	public boolean saveHistoryEntity(String dateOfLastBuild) throws IllegalArgumentException, MalformedURLException,
			DOMException, IOException, FeedException, FetcherException, SAXException, ParserConfigurationException;

	/**
	 * Return true if document doesn't exists </br>
	 * Return false if document exists
	 * 
	 * 
	 * @see #saveHistoryEntity(String)
	 * @param dateOfLastBuild
	 * @return
	 * @throws FetcherException
	 * @throws FeedException
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws IllegalArgumentException
	 */
	public boolean canBeSaved(String dateOfLastBuild, List<String> links)
			throws IllegalArgumentException, MalformedURLException, IOException, FeedException, FetcherException;

	/**
	 * Return true if are links are current.</br>
	 * Return false if some documents should be saved.
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws FeedException
	 * @throws FetcherException
	 */
	public boolean allLinksAreCurrent(List<String> links)
			throws IllegalArgumentException, MalformedURLException, IOException, FeedException, FetcherException;

	/**
	 * 
	 * @return
	 * @throws ParseException
	 */
	public HistoryEntity findLast() throws ParseException;
}
