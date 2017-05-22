package wroclaw.webrest.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import wroclaw.webrest.entity.CurrencyEntity;
import wroclaw.webrest.entity.DocumentEntity;
import wroclaw.webrest.interfaces.CurrencyServiceInterface;
import wroclaw.webrest.repository.CurrencyRepository;

@Service
public class CurrencyService implements CurrencyServiceInterface {

	Logger log = Logger.getLogger(this.getClass().getName());

	@Autowired
	private CurrencyRepository currencyRepository;

	@Autowired
	private XMLDistributorService xmlReaderService;


	public List<CurrencyEntity> saveCurrencyFromDocument(String link, DocumentEntity document)
			throws SAXException, IOException, ParserConfigurationException {
		List<Node> documentTree = xmlReaderService.getCurrencyFromLink(link);
		List<CurrencyEntity> currencyToAdd = new ArrayList<>();
		for (Node node : documentTree) {
			CurrencyEntity currencyEntity = new CurrencyEntity(); // CREATE NEW CURRENCY ENTITY
			distributeAttribute(currencyEntity, node.getChildNodes()); // DISTRIBUTE ATTRIBUTE e.g. CODE,NAME, RATE, etc.
			currencyEntity.setDocument(document); // SET PARENT-DOCUMENT
			currencyEntity.setDate(document.getDate()); // DATE IS FROM DOCUMENT, NOT SERVER
			currencyRepository.save(currencyEntity);
			currencyToAdd.add(currencyEntity);
		}
		return currencyToAdd;
	}


	public CurrencyEntity distributeAttribute(CurrencyEntity currencyEntity, NodeList listOfChild) {
		for (int i = 0; i < listOfChild.getLength(); i++) {
			Node child = listOfChild.item(i);
			if (child.getNodeName().equals("nazwa_waluty")) {
				currencyEntity.setCurrencyName(child.getTextContent());
			} else if (child.getNodeName().equals("przelicznik")) {
				currencyEntity.setConverter(child.getTextContent());
			} else if (child.getNodeName().equals("kod_waluty")) {
				currencyEntity.setCurrencyCode(child.getTextContent());
			} else if (child.getNodeName().equals("kurs_sredni")) {
				currencyEntity.setExchangeRate(child.getTextContent());
			}
		}
		return currencyEntity;
	}

	public List<CurrencyEntity> getAllByName(String name) {
		List<CurrencyEntity> list = new ArrayList<>();
		for (CurrencyEntity currency : currencyRepository.findAll()) {
			if (currency.getCurrencyName().equals(name)) {
				list.add(currency);
			}
		}
		return list;
	}

	@Override
	public List<CurrencyEntity> getAllByCode(String code) {
		List<CurrencyEntity> list = new ArrayList<>();
		for(CurrencyEntity currency : currencyRepository.findAll()) {
			if(currency.getCurrencyCode().equals(code)) {
				list.add(currency);
			}
		}
		return list;
	}

	@Override
	public List<CurrencyEntity> getAllByDocument(String documentCode) {
		List<CurrencyEntity> list = new ArrayList<>();
		for(CurrencyEntity currency : currencyRepository.findAll()) {
			if(currency.getDocument().getInternalCode().equals(documentCode)) {
				list.add(currency);
			}
		}
		return list;
	}
	

	@Override
	public double getAverangeExchange(String code) {
		double sumOfExchange = 0;
		int size = 0;
		for (CurrencyEntity currency : getAllByCode(code)) {
			sumOfExchange += currency.getExchangeRate();
			size++;
		}
		return sumOfExchange / size;
	}
	
	public double againstYesterday(CurrencyEntity currency) throws ParseException {
		List<CurrencyEntity> allByCode = getAllByCode(currency.getCurrencyCode());
		int indexOf = allByCode.indexOf(currency);
		log.warning(indexOf + " size: " + allByCode.size());
		log.warning(currency.getExchangeRate() + " - " +allByCode.get(indexOf-1).getExchangeRate());
		return allByCode.size()==0 ? 0 : (currency.getExchangeRate() - allByCode.get(indexOf-1).getExchangeRate());
	}
	
	public Map<String, String> getCodesWithName() {
		Map<String, String> map = new HashMap<>();
		for(CurrencyEntity currency : currencyRepository.findAll()) {
			if(!map.containsKey(currency.getCurrencyCode())) {
				map.put(currency.getCurrencyCode(), currency.getCurrencyName());
			}
		}
		return map;
	}

}
