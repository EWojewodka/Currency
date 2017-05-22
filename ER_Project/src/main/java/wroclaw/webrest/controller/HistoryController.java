package wroclaw.webrest.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import wroclaw.webrest.entity.HistoryEntity;
import wroclaw.webrest.repository.HistoryRepository;
import wroclaw.webrest.service.HistoryService;

@Controller
@RequestMapping("/history")
public class HistoryController {

	@Autowired
	private HistoryService historyService;

	@Autowired
	private HistoryRepository historyRepository;

	/**
	 * Return in model all history entity
	 * 
	 * @param model
	 * @return {@link HistoryEntity}
	 */
	@RequestMapping("/all")
	public String showAll(Model model) {
		List<HistoryEntity> list = new ArrayList<>();
		for (HistoryEntity history : historyRepository.findAll()) {
			list.add(history);
		}
		model.addAttribute("model", list);
		return "index";
	}

	/**
	 * Return informations about last update.
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/last")
	public String lastUpdate(Model model) throws ParseException {
		List<HistoryEntity> list = new ArrayList<>();
		list.add(historyService.findLast());
		model.addAttribute("model", list);
		return "index";
	}
}
