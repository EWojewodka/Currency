package wroclaw.webrest.interfaces;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.rometools.fetcher.FetcherException;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;

public interface DocumentXMLParserInterface {
	/**
	 * Return document, default set on <a href=
	 * "http://rss.nbp.pl/kursy/TabelaA.xml">http://rss.nbp.pl/kursy/TabelaA.xml</a>.</br>
	 * If you want to change url, go to <b>Config.properties</b>.
	 * 
	 * @see #getParsedXMLDocument(String)
	 * @return {@link Document}
	 * @return
	 * @throws IllegalArgumentException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws FeedException
	 * @throws FetcherException
	 */
	public SyndFeed getFeed()
			throws IllegalArgumentException, MalformedURLException, IOException, FeedException, FetcherException;

	/**
	 * Return document from specified link.
	 * 
	 * @param link
	 * @return
	 * @throws IllegalArgumentException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws FeedException
	 * @throws FetcherException
	 */
	public SyndFeed getFeed(String link)
			throws IllegalArgumentException, MalformedURLException, IOException, FeedException, FetcherException;

	/**
	 * Return parsedDocument, which can be using to getting nodes.
	 * 
	 * @see Node
	 * @param link
	 * @return {@link Document}
	 * @throws MalformedURLException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public Document getParsedXMLDocument(String link)
			throws MalformedURLException, SAXException, IOException, ParserConfigurationException;
}
