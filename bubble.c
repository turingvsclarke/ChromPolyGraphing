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

// This function swaps two values in the values array
 void swap(int* a, int* b){
	int a_value = *a;
	int b_value = *b;
	
	if (a > b){ 
		a--;
		b++; 
	}

	else {
		a++;
		b--;
	}	
	

	values[a-values]=a_value;
	values[b-values]=b_value;

} // end swap function

// This function does the heavy lifting, using swap on every pair of elements in the array
void sort(){
	int i = 0;
	int j = 0;
	for (i=0; i < MAX; i++){
		for (j=0; j<MAX-1; j++){
			if (values[j]<values[j+1]){
				swap(&values[j],&values[j+1]);
				printValues();
	
			}
		} 
	}
} // end sort function

// This is the main function as given by Andy Harris
int main(){
	
	printf("Before: \n");
	printValues();
	sort();
	printf("After: \n");
	printValues();
	return(0);

} // end of main 
