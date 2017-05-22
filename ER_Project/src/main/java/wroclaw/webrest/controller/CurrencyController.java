package wroclaw.webrest.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import wroclaw.webrest.service.CurrencyService;
import wroclaw.webrest.service.XMLDistributorService;

@Controller
@RequestMapping("/currency")
public class CurrencyController {

	Logger log = Logger.getLogger(this.getClass().getName());

	@SuppressWarnings("unused")
	@Autowired
	private CurrencyService currencyService;

	@Autowired
	XMLDistributorService xmlReader;

	@RequestMapping("/{name}/all")
	public String getAllByName(@PathVariable("name") String name, Model model) {
		return "index";
	}
}
