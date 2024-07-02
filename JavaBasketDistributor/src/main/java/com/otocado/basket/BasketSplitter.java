package com.otocado.basket;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.otocado.product.DeliveryMethod;
import com.otocado.services.ConfigFileReader;
import com.otocado.services.PermutationAlgorithm;

public class BasketSplitter {

    private String absolutePathToConfigFile;
    private Map<String, List<String>> productList;
    private Map<String, List<String>> deliveryMethod = new HashMap<>();


    private void setDeliveryMethods() {
        for(String product : productList.keySet()) {
            for (String productDeliveryMethod : productList.get(product)) {
                List<String> temp = new ArrayList<>();
                if (deliveryMethod.containsKey(productDeliveryMethod) == false)
                {
                    temp.add(product);
                   deliveryMethod.put(productDeliveryMethod, temp);
                }
                else {
                    temp = deliveryMethod.get(productDeliveryMethod);
                    temp.add(product);
                    deliveryMethod.put(productDeliveryMethod, temp);
                }
            }
        }

    }



    public BasketSplitter(String absolutePathToConfigFile) {
        this.absolutePathToConfigFile = absolutePathToConfigFile;
    }

    public Map<String, List<String>> split(List<String> items) throws IOException {
        ConfigFileReader productReader = new ConfigFileReader(absolutePathToConfigFile);
        productList = productReader.getProductList(items);
        setDeliveryMethods();

        PermutationAlgorithm hp = new PermutationAlgorithm(deliveryMethod);
        hp.generatePermutations(deliveryMethod, deliveryMethod.keySet().size());
        Map<String, List<String>> optimalSolution = hp.getDeliveryList();

        return optimalSolution;
    }


}
