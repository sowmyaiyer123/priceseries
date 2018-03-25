package com.calculation.engine.controller;

import com.calculation.engine.domain.Price;
import com.calculation.engine.domain.PriceQuote;
import com.calculation.engine.service.PriceQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/priceApp/")
public class PriceQuoteController {

    private PriceQuoteService service;

    @Autowired
    public PriceQuoteController(PriceQuoteService service) {
        this.service = service;
    }

    @RequestMapping(value = "/submitPriceQuote", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void submitPriceQuote(@RequestBody Price price) {
        service.save(new PriceQuote(price.getPrice()));
    }

    @RequestMapping(value = "/getAveragePriceQuote", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public Map getAveragePriceQuote(@RequestParam(value = "numberOfQuotes", required = true) int numberOfQuotes) {
        return Collections.singletonMap("average", service.computeAverageOfLastN(numberOfQuotes));
    }
}
