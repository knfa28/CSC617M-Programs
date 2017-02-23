#include <iostream>
#include <math.h>

using namespace std;

int main(){
	int num;
	cout << "Hello, World!" << endl;
	cout << "Input a number: ";
	cin >> num;
	cout << "Your entered number: " << num << endl;
	
	int i, cnt = 0;
	
	for(i = 1; i < num; i++){
		if( num%i == 0 )
			cnt++; 
	}
	
	if(cnt > 1){
		cout << num << " is not a prime number" << endl;
	} else {
		cout << num << " is a prime number"	<< endl;		
	}
	
	float x;
	x = pow(num, 2) + ((log10(num) - sin(num))/ sqrt(num));
	cout << "f(" << num << ") is " << x << endl;
	
	return 0;
}
