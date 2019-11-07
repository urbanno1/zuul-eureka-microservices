package com.monycell.dbservice.dbservice.resource;

import com.monycell.dbservice.dbservice.model.Quote;
import com.monycell.dbservice.dbservice.model.Quotes;
import com.monycell.dbservice.dbservice.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {
    @Autowired
    private QuoteRepository quoteRepository;

    @GetMapping("/quotelist/{username}")
    public List<String> getQoutes(@PathVariable final String username) {
        return getQuoteByUsername(username);
    }

    @PostMapping("/addquote")
    public  List<String> addQuote(@RequestBody Quotes quotes) {
              quotes.getQuotes().stream()
              .map(quote -> new Quote(quotes.getUsername(), quote))
              .forEach(quote -> quoteRepository.save(quote));

      return  getQuoteByUsername(quotes.getUsername());
    }

    @DeleteMapping("/delete/{username}")
    public List<String> delete(@PathVariable String username) {
        List<Quote> quotes = quoteRepository.findByUsername(username);
        quoteRepository.deleteAll(quotes);
        return getQuoteByUsername(username);
    }

    private List<String> getQuoteByUsername(String username) {
        return quoteRepository.findByUsername(username)
                .stream()
                .map(quote -> {return quote.getQuote();})
                .collect(Collectors.toList());
    }
}
