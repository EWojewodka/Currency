package wroclaw.webrest.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import wroclaw.webrest.entity.DocumentEntity;
import wroclaw.webrest.entity.HistoryEntity;
import wroclaw.webrest.interfaces.DocumentServiceInterface;
import wroclaw.webrest.repository.DocumentRepository;

@Service
public class DocumentEntityService implements DocumentServiceInterface {

	Logger log = Logger.getLogger(this.getClass().getName());

	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private DocumentXMLParser documentXML;

	public DocumentEntity saveDocument(String link, HistoryEntity historyEntity)
			throws DOMException, MalformedURLException, SAXException, IOException, ParserConfigurationException {
		if (canBeSave(link)) {
			DocumentEntity documentEntity = new DocumentEntity();
			documentEntity.setDate(getDocumentPublish(link));
			documentEntity.setLink(link);
			documentEntity.setInternalCode(getDocumentInternalCode(link));
			documentRepository.save(documentEntity);
			documentEntity.setCurrency(currencyService.saveCurrencyFromDocument(link, documentEntity));
			documentEntity.setHistory(historyEntity);
			documentRepository.save(documentEntity);
			return documentEntity;
		} else {
			return null;
		}
	}

	public String getDocumentPublish(String link)
			throws DOMException, MalformedURLException, SAXException, IOException, ParserConfigurationException {
		return documentXML.getParsedXMLDocument(link).getElementsByTagName("data_publikacji").item(0).getTextContent();
	}

	public String getDocumentInternalCode(String link)
			throws DOMException, MalformedURLException, SAXException, IOException, ParserConfigurationException {
		return documentXML.getParsedXMLDocument(link).getElementsByTagName("numer_tabeli").item(0).getTextContent();
	}

	public boolean canBeSave(String link)
			throws DOMException, MalformedURLException, SAXException, IOException, ParserConfigurationException {
		return findByInternalCode(getDocumentInternalCode(link)) == null;
	}

	public DocumentEntity findByDate(String date) {
		for (DocumentEntity documentEntity : documentRepository.findAll()) {
			if (documentEntity.getDate().equals(date)) {
				return documentEntity;
			}
		}
		return null;
	}

	public DocumentEntity findByInternalCode(String code) {
		for (DocumentEntity documentEntity : documentRepository.findAll()) {
			if (documentEntity.getInternalCode().equals(code)) {
				return documentEntity;
			}
		}
		return null;
	}

}
