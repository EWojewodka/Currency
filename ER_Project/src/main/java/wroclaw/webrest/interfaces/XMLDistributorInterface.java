package wroclaw.webrest.interfaces;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.rometools.fetcher.FetcherException;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.FeedException;

import wroclaw.webrest.entity.HistoryEntity;
import wroclaw.webrest.service.HistoryService;

public interface XMLDistributorInterface {

	/**
	 * Return list of nodes from specified link.
	 * 
	 * @param link
	 * @return
	 * @throws FetcherException
	 * @throws FeedException
	 * @throws IllegalArgumentException
	 * @throws MalformedURLException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public List<String> getLinkFromLinkedXML(String link)
			throws IllegalArgumentException, MalformedURLException, IOException, FeedException, FetcherException;
	
	
	/**
	 * Return link from XMLDocument. Links'll find if they are in entry
	 *
	 * @see SyndEntry
	 * @return
	 * @throws FetcherException
	 * @throws FeedException
	 * @throws MalformedURLException
	 * @throws IllegalArgumentException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public List<String> getLinksFromMainXML()
			throws IllegalArgumentException, MalformedURLException, IOException, FeedException, FetcherException;
	

	/**
	 * Last build date is checked. </br>
	 * If database have not a historyEntity with that
	 * {@link HistoryEntity#getDocumentBuildDate()} it will be saved.
	 * 
	 * @see HistoryService#canBeSaved(String)
	 * @return String
	 * @throws IllegalArgumentException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws FeedException
	 * @throws FetcherException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws DOMException
	 */
	public boolean checkDateOfMainXMLBuild() throws IllegalArgumentException, MalformedURLException, IOException,
			FeedException, FetcherException, DOMException, SAXException, ParserConfigurationException ;
	
	/**
	 * Read XML from given link and return all elements which is named
	 * "pozycja". </br>
	 * 
	 * Return all "pozycja" nodes
	 * 
	 * @see Node
	 * @param link
	 * @return
	 * @throws MalformedURLException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public List<Node> getCurrencyFromLink(String link)
			throws MalformedURLException, SAXException, IOException, ParserConfigurationException;
}
