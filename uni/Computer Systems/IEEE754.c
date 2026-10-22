#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

char* float_to_ieee754(float num) {
    static char output[35];
    unsigned int *p = (unsigned int *)&num;
    for (int i = 0; i < 32; i++) {
        output[i] = (*p & (1u << (31 - i))) ? '1' : '0';
    }
    output[32] = '\0';
    return output;
}
int main(){
    float input;
    printf("Enter a float number: ");
    scanf("%f", &input);
    char* ieee754 = float_to_ieee754(input);
    printf("IEEE 754 representation: %s\n", ieee754);
    return 0;
}
