package com.monycell.stockservice.stockservice.resource;

import com.monycell.stockservice.stockservice.model.Quote;
import com.monycell.stockservice.stockservice.model.StockQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("rest/stock")
public class StockResourceService {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/stockinfo/{username}")
    public List<Quote> getStock(@PathVariable String username) {
         ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://db-service/rest/db/quotelist/"+ username,  HttpMethod.GET,null,
                new ParameterizedTypeReference<>(){});
            List<String> quotes = quoteResponse.getBody();

           return quotes
                   .stream()
                   .map(quote -> {
                        Stock stock = getStockPrice(quote);
                       return new Quote(quote, stock.getQuote().getPrice());
                    })
                   .collect(Collectors.toList());
    }

    private Stock getStockPrice(String quote) {
        try {
            return  YahooFinance.get(quote);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Stock(quote);
    }
}
