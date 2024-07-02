#include <iostream>
#include <curl/curl.h>
#include <pugixml.hpp>
#include <sstream>
#include "Fetcher.cpp"

class PowiatFetcher : public Fetcher {
private:
    CURL *curl;
    CURLcode res;
    std::string readBuffer;
    std::string powiatUrl;

    pugi::xml_document doc;
    pugi::xml_parse_result result;

    static size_t WriteCallback(void *contents, size_t size, size_t nmemb, void *userp) {
        ((std::string *) userp)->append((char *) contents, size * nmemb);
        return size * nmemb;
    }

public:
    PowiatFetcher(const std::string& powiatUrl) : powiatUrl(powiatUrl), curl(nullptr), res(CURLE_OK) {}

    void fetch() override {
        std::string urlName = powiatUrl;
        curl_global_init(CURL_GLOBAL_DEFAULT);
        curl = curl_easy_init();
        if (curl) {
            curl_easy_setopt(curl, CURLOPT_URL, urlName.c_str());
            curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteCallback);
            curl_easy_setopt(curl, CURLOPT_WRITEDATA, &readBuffer);

            res = curl_easy_perform(curl);
            if (res != CURLE_OK) {
                fprintf(stderr, "curl_easy_perform() failed: %s\n", curl_easy_strerror(res));
            }
            curl_easy_cleanup(curl);
        }
        curl_global_cleanup();

        result = doc.load_string(readBuffer.c_str());
        if (result) std::cout << "XML parsed successfully" << std::endl;
    }

    const pugi::xml_parse_result& getResult() const {
        return result;
    }

    const pugi::xml_document& getDocument() const {
        return doc;
    }
};
