package wroclaw.webrest.service;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.rometools.fetcher.FetcherException;
import com.rometools.rome.io.FeedException;

@Component
public class AutoUpdate {

	@Autowired
	private XMLDistributorService xmlDistributor;
	
	/**
	 * 
	 * @throws IllegalArgumentException
	 * @throws MalformedURLException
	 * @throws DOMException
	 * @throws IOException
	 * @throws FeedException
	 * @throws FetcherException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	@Scheduled(fixedDelay = 10000)
	public void autoUpdate() throws IllegalArgumentException, MalformedURLException, DOMException, IOException, FeedException, FetcherException, SAXException, ParserConfigurationException {
		xmlDistributor.checkDateOfMainXMLBuild();
	}
}
