package com.otocado.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BasketReader {
    private String absolutePathToBasketFile;
    private List<String> basket;

    public BasketReader(String absolutePathToBasketFile) {
        this.absolutePathToBasketFile = absolutePathToBasketFile;
    }

    public List<String> getBasket() throws IOException {
        ObjectMapper configFileMapper = new ObjectMapper();
        basket = configFileMapper.readValue(new File(absolutePathToBasketFile), new TypeReference<ArrayList<String>>() {});
        return basket;
    }


}
