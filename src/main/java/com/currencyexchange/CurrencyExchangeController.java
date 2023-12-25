package com.currencyexchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	private static final String UNABLE_TO_FIND_CURRENCY_EXCHANGE_FROM = "Unable to find currency exchange from ";

	@Autowired
	private Environment environment;

	@Autowired
	private CurrencyExchangeRepository ceRepository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange currencyExchange(@PathVariable String from, @PathVariable String to) {
		CurrencyExchange currencyExchange = ceRepository.findByFromAndTo(from, to)
				.orElseThrow(() -> new RuntimeException(UNABLE_TO_FIND_CURRENCY_EXCHANGE_FROM + from + " to " + to));
//		CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}
}
