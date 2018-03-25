package com.calculation.engine;

import com.calculation.engine.domain.PriceQuote;
import com.calculation.engine.repo.PriceQuoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PriceQuoteRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PriceQuoteRepository priceQuoteRepository;

    @Test
    public void testFindByName() {
        entityManager.persist(new PriceQuote(new BigDecimal(100)));
        entityManager.persist(new PriceQuote(new BigDecimal(200)));
        entityManager.persist(new PriceQuote(new BigDecimal(300)));
        BigDecimal result = priceQuoteRepository.computeAverageOfLastN(2);
        assertEquals(250, result.intValue());
        result = priceQuoteRepository.computeAverageOfLastN(3);
        assertEquals(200, result.intValue());
    }
}

