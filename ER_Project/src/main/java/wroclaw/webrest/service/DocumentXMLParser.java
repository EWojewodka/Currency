package wroclaw.webrest.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.rometools.fetcher.FetcherException;
import com.rometools.fetcher.impl.HashMapFeedInfoCache;
import com.rometools.fetcher.impl.HttpURLFeedFetcher;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;

import wroclaw.webrest.interfaces.DocumentXMLParserInterface;
import wroclaw.webrest.utils.Config;

@Service
public class DocumentXMLParser implements DocumentXMLParserInterface {

	public SyndFeed getFeed()
			throws IllegalArgumentException, MalformedURLException, IOException, FeedException, FetcherException {
		return new HttpURLFeedFetcher(HashMapFeedInfoCache.getInstance()).retrieveFeed(new URL(Config.getString("url")));
	}
	
	public SyndFeed getFeed(String link) throws IllegalArgumentException, MalformedURLException, IOException, FeedException, FetcherException {
		return new HttpURLFeedFetcher(HashMapFeedInfoCache.getInstance()).retrieveFeed(new URL(link));
	}

	public Document getParsedXMLDocument(String link)
			throws MalformedURLException, SAXException, IOException, ParserConfigurationException {
		return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new URL(link + " ").openStream());
	}
}
