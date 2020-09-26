//fileIO.cpp

#include <fstream>
#include <iostream>
#include <string>
#include <sstream>

int main(){
	std::ofstream outFile;
	std::ifstream inFile;
	std::string currentLine;
	std::string converter;
	std::string inputStr;
	std::stringstream ss;
	int repetition;
	int int1;

	// we're going to read from a file. Next we're going to output to a file
	outFile.open("output.txt");
	inFile.open("input.txt");
	while (getline(inFile, currentLine)){
	
		//////////// Input section
		
		// we first pass the current line to an empty stream
		ss.clear();
		ss.str("");
		ss << currentLine;	

		repetition = 0;
		// We loop through the line we got, adding up the integers by first parsing the line into the three integers
		for(int i=0;i<3;i++){  

	      
			ss >> int1 >> converter;  
			converter = converter.substr(1,converter.length()-1);
			ss.clear();
			ss.str("");
			ss << converter;
			repetition = repetition+int1; 

				 } // end for

		getline(inFile, currentLine);
		inputStr = currentLine;  

		////////////// Output section
		
		// Print out the keyword a sum of the integers number of times
		if (outFile.is_open()){
			for(int i=0;i<repetition;i++){

				outFile<<inputStr<<",";

				} // end for
			outFile<<std::endl;			
			} // end if

		 else {
			std::cout << "unable to access file 'output.txt' to write"<< std::endl; 
			} // end else
		
		
	} // end while

	return 0;

} // end main











