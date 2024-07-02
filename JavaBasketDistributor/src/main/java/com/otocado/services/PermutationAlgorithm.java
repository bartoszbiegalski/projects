package com.otocado.services;

import com.otocado.types.ListPair;

import java.util.*;

public class PermutationAlgorithm {
    private List<String> allProducts = new ArrayList<>();
    private Map<String, List<String>> deliveryMethods = new HashMap<>();


    private List<ListPair> finalArrayMap = new ArrayList<>();
    private int minimalDeliveryCount = -1;

    private List<String> first = new ArrayList<>();

    private List<String> second = new ArrayList<>();

    public PermutationAlgorithm(Map<String, List<String>> deliveryMethod) {
        this.deliveryMethods = deliveryMethod;
        setProducts();
    }

    public void setProducts() {
        Set<String> unique = new HashSet<>();

        for (String deliveryMethod : deliveryMethods.keySet()) {
            unique.addAll(deliveryMethods.get(deliveryMethod));
        }
        allProducts = new ArrayList<>(unique);
        Collections.sort(allProducts);
    }

    public void generatePermutations(Map<String, List<String>> newProductList, int n) {

        for (int i = 1; i <= n; i++) {
            if (finalArrayMap.isEmpty() == false) {
                return;
            }
            generatePermutationsHelper(deliveryMethods, new ArrayList<>(), new ArrayList<>(), i);
        }


    }
    private void generatePermutationsHelper(Map<String, List<String>> deliveryMethods, List<String> currentDeliveryMethods, List<String> products, int length) {
            if(currentDeliveryMethods.size() == length) {
                first = new ArrayList<>(currentDeliveryMethods);

                HashSet<String> uniqueProducts = new HashSet<>(products);
                second = new ArrayList<>(uniqueProducts);
                Collections.sort(second);
                if (second.equals(allProducts)) {
                    if (minimalDeliveryCount < 0) {
                        finalArrayMap.add(new ListPair(first, second));
                        minimalDeliveryCount = first.size();
                    }
                    else {
                        if (minimalDeliveryCount < first.size()) {
                            return;
                        }
                        else {
                            finalArrayMap.add(new ListPair(first, second));
                        }
                    }
                    finalArrayMap.add(new ListPair(first, second));
                }
                return;
            }
        for (String key : deliveryMethods.keySet()) {
            if (!currentDeliveryMethods.contains(key)) {

                currentDeliveryMethods.add(key);
                List<String> temp = new ArrayList<>(deliveryMethods.get(key));
                List<String> temp1 = new ArrayList<>(products);
                products.addAll(deliveryMethods.get(key));

                temp.removeAll(temp1);
                generatePermutationsHelper(deliveryMethods, currentDeliveryMethods, products, length);
                currentDeliveryMethods.remove(currentDeliveryMethods.size() - 1);
                products.removeAll(temp);

            }
        }
    }

    private void sortSet(Map<String, List<String>> set) {
        List<Map.Entry<String, List<String>>> lista = new ArrayList<>(set.entrySet());
        Collections.sort(lista, new Comparator<Map.Entry<String, List<String>>>() {
            @Override
            public int compare(Map.Entry<String, List<String>> entry1, Map.Entry<String, List<String>> entry2) {
                return Integer.compare(entry2.getValue().size(), entry1.getValue().size());
            }
        });
    }

    private int findMaxArrayName(List<String> names, List<List<String>> values) {
        int max = -1;
        int index = 0;

        for (int i = 0; i < names.size(); i++) {
            if (max < values.get(i).size()) {
                max = values.get(i).size();
                index = i;
            }
        }

        return index;
    }


    public Map<String, List<String>> getDeliveryList() {

        Map<List<String>, List<List<String>>> tempMap = new HashMap<>();

        for (ListPair listPair : finalArrayMap) {
            List<List<String>> values =  new ArrayList<>();
            for (String name : listPair.getFirst()) {
                values.add(new ArrayList<>(deliveryMethods.get(name)));
            }
            tempMap.put(listPair.getFirst(), values);
        }

        Map<List<String>, List<List<String>>> finalMap = new HashMap<>();

        for (List<String> names: tempMap.keySet()) {
            List<String> tempNames = new ArrayList<>(names);
            List<List<String>> tempValues = new ArrayList<>(tempMap.get(names));

            List<String> finalNames = new ArrayList<>();
            List<List<String>> finalValues = new ArrayList<>();

            while (tempNames.isEmpty() == false) {
                int indexOfBiggestArray = findMaxArrayName(tempNames, tempValues);
                finalNames.add(tempNames.get(indexOfBiggestArray));
                finalValues.add(tempValues.get(indexOfBiggestArray));

                for (List<String> array : tempValues) {
                    if (array != tempValues.get(indexOfBiggestArray)) {
                        array.removeAll(new ArrayList<>(tempValues.get(indexOfBiggestArray)));
                    }
                }

                tempNames.remove(indexOfBiggestArray);
                tempValues.remove(indexOfBiggestArray);
            }
            finalMap.put(finalNames, finalValues);

        }

        for (int i = 0; i < minimalDeliveryCount; i++) {

            int max = -1;

            for (List<String> name : finalMap.keySet()) {
                ArrayList<String> tempNames = new ArrayList<>(name);
                ArrayList<List<String>> tempValues = new ArrayList<>(finalMap.get(name));
                if (max < tempValues.get(findMaxArrayName(tempNames, tempValues)).size()) {
                    max = tempValues.get(findMaxArrayName(tempNames, tempValues)).size();
                }

            }
            Iterator<List<String>> iterator = finalMap.keySet().iterator();
            while (iterator.hasNext()) {
                List<String> name = iterator.next();
                ArrayList<String> tempNames = new ArrayList<>(name);
                ArrayList<List<String>> tempValues = new ArrayList<>(finalMap.get(name));

                if (max > tempValues.get(findMaxArrayName(tempNames, tempValues)).size()) {
                    iterator.remove();
                }
            }

        }


        for (List<String> names : finalMap.keySet()) {
            Map<String, List<String>> map = new HashMap<>();
            for (int i = 0; i < names.size(); i++) {
                map.put(names.get(i), finalMap.get(names).get(i));
            }

            return map;

        }
        return null;
    }
}
