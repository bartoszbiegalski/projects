package com.otocado;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otocado.basket.BasketSplitter;
import com.otocado.services.BasketReader;
import com.otocado.services.ConfigFileReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        BasketSplitter basketSplitter = new BasketSplitter("/home/bartek/Documents/Docs/Zadanie/ZadanieRekrutacyjny/src/main/resources/config.json");
        BasketReader basketReader = new BasketReader("/home/bartek/Documents/Docs/Zadanie/ZadanieRekrutacyjny/src/main/resources/basket-2.json");

        Map<String, List<String>> solution = basketSplitter.split(basketReader.getBasket());

        System.out.println(solution.keySet() + " " + solution.values());
    }
}
