package com.calculation.engine.service;

import com.calculation.engine.domain.PriceQuote;
import com.calculation.engine.repo.PriceQuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PriceQuoteService {

    private PriceQuoteRepository repository;

    @Autowired
    public PriceQuoteService(PriceQuoteRepository repository) {
        this.repository = repository;
    }

    public BigDecimal computeAverageOfLastN(int numberOfQuotes) {
        return repository.computeAverageOfLastN(numberOfQuotes);
    }

    public void save(PriceQuote priceQuote) {
        repository.save(priceQuote);
    }
}
