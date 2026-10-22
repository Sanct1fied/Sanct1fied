#include <stdio.h>
#include <stdlib.h>
#include <string.h>



int main(){
    printf("Enter your 10 digit ISBN number: ");
    char isbn[11];
    int sum = 0;
    scanf("%10s", isbn);
    for (int i = 0; i < 9; i++) {
        isbn[i] = isbn[i] - '0'; // Convert char to int
        isbn[i] = isbn[i] * (10 - i); 
        sum += isbn[i]; // Add to sum
    }
    int mod = sum % 11;
    printf("%d\n", mod);
    int calculatedCheckDigit = 11-mod;
    int checkdigit = isbn[9] - '0'; // Convert char to int
    if (calculatedCheckDigit == checkdigit) {
        printf("The ISBN number is valid.\n");
    } else if (mod == 10 && isbn[9] == 'X') {
        printf("The ISBN number is valid.\n");
    } else {
        printf("The ISBN number is invalid.\n");
    }
}
