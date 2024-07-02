#include "PowiatFetcher.cpp"
#include "PowiatParser.cpp"
#include "PowiatReader.cpp"

int main() {
    //PowiatFetcher powiatFetcher("https://wms.sip.piekary.pl/piekary-egib?service=WFS&version=1.1.0&request=GetFeature&typename=dzialki&outputformat=GML3");
    //powiatFetcher.fetch();
    PowiatReader powiatReader("dzialki.gml");
    powiatReader.read();
    powiatReader.checkIntegrality();
    //PowiatParser powiatParser(powiatFetcher.getDocument());
    //powiatParser.parse();
}

