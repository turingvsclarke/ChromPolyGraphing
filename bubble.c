#include <stdio.h>
#define MAX 9

int values[] = {7, 3, 9, 4, 6, 1, 2, 8, 5};

// this function prints out the values array by printing each value individually
void printValues(){
	int i=0;
        printf("[");
	for (i = 0; i < MAX; i++){
  		printf(" %d ",values[i]);
	} // end for

	printf("] \n");
}  // end printValues function


// This function does the heavy lifting, using swap on every pair of elements in the array
//void sort(){}

	

 // This function does the heavy lifting, taking in an array and using swap to order it
void swap(int* a, int* b){

	int* c = a;
	int* d = b;
	a = d;
	b = c;		

} // end swap function



int main(){
	
	printf("Before: \n");
	printValues();
	//sort();
	printf("After: \n");
	//printValues();

	return(0);

} // end of main 
