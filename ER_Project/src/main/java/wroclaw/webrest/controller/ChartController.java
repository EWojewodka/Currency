package wroclaw.webrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import wroclaw.webrest.service.CurrencyService;

@Controller
@RequestMapping("/charts")
public class ChartController {

	@Autowired
	private CurrencyService currencyService;
	
	@RequestMapping("/settings")
	public String showSettings(Model model) {
		model.addAttribute("currencyName", currencyService.getCodesWithName());
		return "chart";
	}
	
}
