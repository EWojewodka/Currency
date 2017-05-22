package wroclaw.webrest.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.rometools.fetcher.FetcherException;
import com.rometools.rome.io.FeedException;

import wroclaw.webrest.entity.CurrencyEntity;
import wroclaw.webrest.entity.DocumentEntity;
import wroclaw.webrest.repository.DocumentRepository;
import wroclaw.webrest.service.DocumentEntityService;
import wroclaw.webrest.service.XMLDistributorService;
import wroclaw.webrest.utils.Utils;

@Controller
@RequestMapping("/document")
public class DocumentController {

	@Autowired
	private XMLDistributorService xmlDistributor;
	
	@Autowired
	private DocumentRepository documentRepository;

	/**
	 * This method try to update database dates.
	 * 
	 * @see DocumentEntityService#update()
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws MalformedURLException
	 * @throws DOMException
	 * @throws IOException
	 * @throws FeedException
	 * @throws FetcherException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	@RequestMapping("/update")
	public String update(Model model) throws IllegalArgumentException, MalformedURLException, DOMException, IOException, FeedException, FetcherException, SAXException, ParserConfigurationException {
		if(xmlDistributor.checkDateOfMainXMLBuild()) {
			model.addAttribute("model", "Aktualizacja przebiegła pomyślnie.");
		} else { 
			model.addAttribute("model","Twoje dany są aktualne");
		}
		return "index";
	}
	
	@RequestMapping("/showAll")
	public String showDocument(Model model) throws ParseException {
		List<DocumentEntity> listOfDocument = new ArrayList<>();
		List<DocumentEntity> list = com.google.common.collect.Lists.newArrayList(documentRepository.findAll());
		Utils.sortDocumentByDate(list);
		for(DocumentEntity document : list) {
			listOfDocument.add(document);
		}
		model.addAttribute("model", listOfDocument);
		return "index";
	}
	
	/**
	 * Show index view with documents currency.</br>
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/{id}/show")
	public  String showOne(@PathVariable("id") int id, Model model) {
		List<Object> modelList = new ArrayList<>(); 
		DocumentEntity document  = documentRepository.findOne(id);
		modelList.add( document );
		for(CurrencyEntity currency : document.getCurrency()) {
			modelList.add(currency);
		}
		model.addAttribute("model", modelList );
		return "index";
	}

}
