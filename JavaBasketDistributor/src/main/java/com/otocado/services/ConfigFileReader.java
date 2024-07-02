package com.otocado.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigFileReader {
    private String absolutePathToConfigFile;

    public ConfigFileReader(String absolutePathToConfigFile) {
        this.absolutePathToConfigFile = absolutePathToConfigFile;
    }

    public Map<String, List<String>> getProductList(List<String> items) throws IOException {
        ObjectMapper configFileMapper = new ObjectMapper();
        Map<String, List<String>> dataMap = configFileMapper.readValue(new File(absolutePathToConfigFile), Map.class);
        Map<String, List<String>> returnMap = new HashMap<>();
        for (String product : items) {
            if (dataMap.containsKey(product)) {
                returnMap.put(product, dataMap.get(product));
            }
        }

        return returnMap;
    }

}
