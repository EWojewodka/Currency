package wroclaw.webrest.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.rometools.fetcher.FetcherException;
import com.rometools.rome.io.FeedException;

import wroclaw.webrest.entity.DocumentEntity;
import wroclaw.webrest.entity.HistoryEntity;
import wroclaw.webrest.interfaces.HistoryServiceInterface;
import wroclaw.webrest.repository.DocumentRepository;
import wroclaw.webrest.repository.HistoryRepository;

@Service
public class HistoryService implements HistoryServiceInterface {

	Logger log = Logger.getLogger(this.getClass().getName());

	@Autowired
	private HistoryRepository historyRepository;

	@Autowired
	private DocumentEntityService documentService;

	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private XMLDistributorService xmlDistributor;

	public boolean saveHistoryEntity(String dateOfLastBuild) throws IllegalArgumentException, MalformedURLException,
			DOMException, IOException, FeedException, FetcherException, SAXException, ParserConfigurationException {
		List<String> links = xmlDistributor.getLinksFromMainXML(); //GET ALL ENCLOUSERS LINK FROM MAIN XML
		if (canBeSaved(dateOfLastBuild, links)) {
			HistoryEntity newHistoryEntity = new HistoryEntity();
			List<DocumentEntity> listOfDocuments = new ArrayList<>();
			newHistoryEntity.setDocumentBuildDate(dateOfLastBuild.toString());
			newHistoryEntity.setDate(new Date());
			historyRepository.save(newHistoryEntity);
			for (String link : links) {
				DocumentEntity documentEntity = documentService.saveDocument(link, newHistoryEntity); //CREATE AND SAVE IN DATABASE. RETURN CREATED & SAVED DOCUMENT
				if (documentEntity != null) { //IF DOCUMENT IS CREATED
					listOfDocuments.add(documentEntity); //THIS DOCUMENT WILL BE CHILD OF HISTORYENTITY
				}
			}
			newHistoryEntity.setDocuments(listOfDocuments);
			historyRepository.save(newHistoryEntity);
			return true;
		}
		return false;
	}

	public boolean canBeSaved(String dateOfLastBuild, List<String> links)
			throws IllegalArgumentException, MalformedURLException, IOException, FeedException, FetcherException {
		boolean canBeSaved = true;
		for (HistoryEntity historyEntity : historyRepository.findAll()) {
			if (dateOfLastBuild.equals(historyEntity.getDocumentBuildDate()) && allLinksAreCurrent(links)) {
				canBeSaved = false;
			}
		}
		return canBeSaved;
	}

	public boolean allLinksAreCurrent(List<String> links)
			throws IllegalArgumentException, MalformedURLException, IOException, FeedException, FetcherException {
		for (DocumentEntity document : documentRepository.findAll()) {
			if (links.contains(document.getLink())) { 	//IF LIST OF LINKS FROM URL HAS LINK FROM DATABASE [...]
				links.remove(document.getLink())	; // THAT LINK WILL BE REMOVED.
			}
		}
		return links.isEmpty();
	}

	public HistoryEntity findLast() throws ParseException {
		ArrayList<HistoryEntity> all = new ArrayList<>();
		for (HistoryEntity history : historyRepository.findAll()) {
			all.add(history);
		}
		log.warning(all.size() + " ");
		return all.get(all.size() - 1);
	}
}
