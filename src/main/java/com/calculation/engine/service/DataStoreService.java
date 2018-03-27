package com.calculation.engine.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Service
public class DataStoreService {
    List<Double> priceData;

    DataStoreService() {
        priceData = new ArrayList<>();
    }

    public void storePrice(double price) {
        priceData.add(price);
    }

    public double computeAverageOfLastN(int numberOfquotes) {
        int numberOfElements = priceData.size();
        int elementsToSkip = numberOfElements - numberOfquotes;
        if (elementsToSkip < 0) {
            elementsToSkip = 0;
        }
        OptionalDouble average = priceData.stream().skip(elementsToSkip).mapToDouble(d -> d).average();
        return average.orElse(0.0);
    }

}
