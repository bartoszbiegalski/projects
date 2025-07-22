#include <iostream>
#include "FileImport.hpp"

int main(int argc, char *argv[])
{

	FileImport fi;
	fi.importGML(argv[1]);
	fi.parseXML();
	fi.retriveNamespaces();

	std::cout << "Import ended succesfuly.\n";

	while (true)
	{
		std::cout << "Your choice:\n";
		std::cout << "1. Divide gml\n";
		std::cout << "2. Exit\n";

		std::string input;
		std::getline(std::cin, input);

		int choice = std::atoi(input.c_str());
		switch (choice)
		{
		case 1:
			std::cout << "lala\n";
			break;
		case 2:
			return 0;
		}
	}

	/// Dzielenie pliku

	// fi.generateReport(atoi(argv[2]));
	//  fi.validateNamespaces();
}
