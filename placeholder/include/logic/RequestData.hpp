/**
 * @file .hpp
 * @class RequestData
 * @brief Class containing methods with different data fetching of gml-files
 * @author Bartosz Biegalski
 * @date 17.03.2025
 */

#include "ogrsf_frmts.h"  // OGR - moduł wektorowy GDAL
#include "gdal_priv.h"

class RequestData
{
private:
    GDALDataset *dataset;
public:
    GDALDataset* getDataSet(const char* fileName);

    GDALDataset* tempAllFunction(const char* filename);
};