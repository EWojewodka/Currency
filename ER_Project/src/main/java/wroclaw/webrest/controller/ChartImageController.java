package wroclaw.webrest.controller;

import java.awt.BasicStroke;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import wroclaw.webrest.entity.CurrencyEntity;
import wroclaw.webrest.repository.CurrencyRepository;
import wroclaw.webrest.service.CurrencyService;
import wroclaw.webrest.utils.Utils;

@Controller
@RequestMapping("/charts/image")
public class ChartImageController {

	@Autowired
	CurrencyRepository currencyRepository;

	@Autowired
	CurrencyService currencyService;

	/**
	 * This metod forward to image with chart of currency. Using JFreeChart
	 * because it's lightweight and easy framework.
	 * 
	 * @param name
	 * @param response
	 * @throws ParseException 
	 */
	@RequestMapping("/{code}")
	public void drawRandomChart(@PathVariable("code") String code, HttpServletResponse response) throws ParseException {
		List<CurrencyEntity> listOfCurrency = currencyService.getAllByCode(code);
		JFreeChart chart = ChartFactory.createLineChart(listOfCurrency.get(0).getCurrencyName(), "Dzień", "kurs",
				createDataset(listOfCurrency), PlotOrientation.VERTICAL, false, true, false);
		CategoryPlot plot = chart.getCategoryPlot();
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
		changeLine(chart);
		try {
			ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, 1300, 700);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private DefaultCategoryDataset createDataset(List<CurrencyEntity> list) throws ParseException {
		Utils.sortCurrencyByDate(list);
		DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
		for (CurrencyEntity currency : list) {
			defaultCategoryDataset.addValue(currency.getExchangeRate(), "kurs", currency.getDate());
		}
		return defaultCategoryDataset;
	}
	
	private void changeLine(JFreeChart chart) {
		CategoryPlot plot = chart.getCategoryPlot();
		int seriesCount = plot.getDomainAxisCount();
		for (int i = 0; i < seriesCount; i++) {
			plot.getRenderer().setSeriesStroke(i, new BasicStroke(4));
		}
	}

}
