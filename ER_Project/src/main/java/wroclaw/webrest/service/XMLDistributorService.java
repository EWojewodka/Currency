package wroclaw.webrest.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.rometools.fetcher.FetcherException;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.FeedException;

import wroclaw.webrest.interfaces.XMLDistributorInterface;

/**
 * 
 * This class allow distribute XML files. </br>
 * 
 *@see #getLinksFromLinkedXML()
 *@see #getLinksFromMainXML()
 *@see #checkDateOfMainXMLBuild()
 *@see #getCurrencyFromLink(String)
 *
 */
@Service
public class XMLDistributorService implements XMLDistributorInterface {

	@SuppressWarnings("unused")
	private Logger log = Logger.getLogger(this.getClass().getName());

	@Autowired
	private DocumentXMLParser documentXML;

	@Autowired
	private HistoryService historyService;

	public List<String> getLinkFromLinkedXML(String link)
			throws IllegalArgumentException, MalformedURLException, IOException, FeedException, FetcherException {
		List<String> links = new ArrayList<>();
		for (SyndEntry entry : documentXML.getFeed(link).getEntries()) {
			links.add(entry.getLink());
		}
		return links;
	}

	public List<String> getLinksFromMainXML()
			throws IllegalArgumentException, MalformedURLException, IOException, FeedException, FetcherException {
		List<String> linksToExchangeRate = new ArrayList<>();
		for (SyndEntry entry : documentXML.getFeed().getEntries()) {
			linksToExchangeRate.add(entry.getEnclosures().get(0).getUrl());
		}
		return linksToExchangeRate;
	}

	public boolean checkDateOfMainXMLBuild() throws IllegalArgumentException, MalformedURLException, IOException,
			FeedException, FetcherException, DOMException, SAXException, ParserConfigurationException {
		Date lastBuildDate = documentXML.getFeed().getPublishedDate();
		return historyService.saveHistoryEntity(lastBuildDate.toString());
	}

	public List<Node> getCurrencyFromLink(String link)
			throws MalformedURLException, SAXException, IOException, ParserConfigurationException {
		List<Node> tree = new ArrayList<>();
		NodeList nodeList = documentXML.getParsedXMLDocument(link).getDocumentElement().getElementsByTagName("pozycja");
		for (int i = 0; i < nodeList.getLength(); i++) {
			tree.add(nodeList.item(i));
		}
		return tree;
	}

}
