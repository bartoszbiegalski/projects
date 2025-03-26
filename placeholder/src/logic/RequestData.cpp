#include <iostream>
#include "logic/RequestData.hpp"

GDALDataset *RequestData::getDataSet(const char *filename)
{
    return (GDALDataset *)GDALOpenEx(filename, GDAL_OF_VECTOR, NULL, NULL, NULL);
}

GDALDataset* RequestData::tempAllFunction(const char* filename)
{
    GDALAllRegister(); /// rejestracja sterowników <- to się powinno wykonać na samym początku działania modułu

    // Ścieżka do pliku GML
    
    // Otwieranie pliku GML
    GDALDataset* poDataset = (GDALDataset*)GDALOpenEx(filename, GDAL_OF_VECTOR, NULL, NULL, NULL);

    // Sprawdzanie, czy plik został poprawnie otwarty
    if (poDataset == NULL) {
        std::cerr << "Nie udało się otworzyć pliku GML!" << std::endl;
        return nullptr;
    }

    // Wypisywanie informacji o warstwach w pliku GML
    int layerCount = poDataset->GetLayerCount();
    std::cout << "Liczba warstw w pliku: " << layerCount << std::endl;

    for (int i = 0; i < layerCount; ++i) {
        OGRLayer* poLayer = poDataset->GetLayer(i);
        std::cout << "Warstwa " << i + 1 << ": " << poLayer->GetName() << std::endl;
    }

    // Zamknięcie pliku
    GDALClose(poDataset);
    return nullptr;
}