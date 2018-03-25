package com.calculation.engine.repo;

import com.calculation.engine.domain.PriceQuote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigDecimal;

@RepositoryRestResource(exported = false)
public interface PriceQuoteRepository extends CrudRepository<PriceQuote, Integer> {

    @Query(value = "SELECT avg(resultPrice.price) FROM (SELECT * from price_quote order by id DESC LIMIT ?1) AS resultPrice", nativeQuery = true)
    public BigDecimal computeAverageOfLastN(int numberOfQuotes);

}
