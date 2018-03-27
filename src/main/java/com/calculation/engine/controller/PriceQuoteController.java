package com.calculation.engine.controller;

import com.calculation.engine.service.DataStoreService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/priceApp/")
public class PriceQuoteController {

    private DataStoreService dataStoreService;

    @Autowired
    public PriceQuoteController(DataStoreService dataStoreService) {
        this.dataStoreService = dataStoreService;
    }

    @RequestMapping(value = "/getAveragePriceQuote", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public Map getAveragePriceQuote(@RequestParam(value = "numberOfQuotes", required = true) int numberOfQuotes) {
        Preconditions.checkArgument(numberOfQuotes >= 0, "Number of quotes should be greater than or equal to 0");
        return Collections.singletonMap("average", dataStoreService.computeAverageOfLastN(numberOfQuotes));
    }
}
