Ryan Eades
File I/O

Main function: The main function will start with declaring all variables used in the various local loops and procedures described below. The majority of the main function is a while loop whose sentry variable is a boolean indicating whether or not the input file still has more lines. This while loop will input two lines of the input file at a time, converting them into the correct line to add to the output file.
	
	1. Input

		A. We clear a stringstream and pass the currentLine to it. 
		B. To parse the currentLine and obtain the three integers from it, we will step into a loop.
			1. The loop will run 3 times, finding another integer each time. It adds the next integer to an accumulator to get their sum.
			2.  During each run, we pass the stream to an integer and string, since we know the line will have an integer followed by a comma. We take a substring of the remaining chunk to get rid of the comma and loop this process.
		C. We make another getline call to get the string we will print, which is the entire next line under the integers.

	2. Output

		A. We check to make sure that the output file is open.
		B. We loop a statement that outputs a copy of the string to the file with a comma appended every time we loop. The number of times is determined by the accumulated value in the input section.
