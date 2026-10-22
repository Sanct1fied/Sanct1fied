#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<time.h>

int randint(int min, int max) {
    return rand() % (max - min + 1) + min;
}

void hex(){
    int num;
    num = randint(0,255);
    printf("Represent this decimal number in hexadecimal: %d\n", num);
    int input;
    printf("Enter the hexadecimal representation: ");
    scanf("%X", &input);
    if (input == num) {
        printf("Correct!\n");
    } else {
        printf("Incorrect. The correct answer is: %X\n", num);
    }
}

void binary(){
    int num;
    num = randint(0,255);
    printf("Represent this decimal number in binary: %d\n", num);
    char input[9];
    printf("Enter the binary representation: ");
    scanf("%s", input);
    int answer = 0;
    for (int i=0;i<8;i++){
        if (input[i] == '1'){
            answer += (1 << (7-i));
        }
    }
    char correct_answer[9];
    for (int i = 7; i >= 0; i--) {
        int bit = (num >> i) & 1;
        correct_answer[7 - i] = bit ? '1' : '0';
    }
    correct_answer[8] = '\0';
    printf("You entered: %d\n", answer);
    if (answer == num) {
        printf("Correct!\n");
    } else {
        printf("Incorrect. The correct answer is: %s\n", correct_answer);
    }
}
char* float_to_ieee754(float num) {
    static char output[35];
    unsigned int *p = (unsigned int *)&num;
    for (int i = 0; i < 32; i++) {
        output[i] = (*p & (1u << (31 - i))) ? '1' : '0';
    }
    output[32] = '\0';
    return output;
}
void IEEE754(){
    float num;
    num = (float)((randint(0,1) ? 1 : -1) * (randint(0, 255) + randint(0,3) * 0.25f));
    printf("Represent this decimal number in IEEE 754 format: %f\n", num);
    char input[35];
    printf("Enter the IEEE 754 representation: ");
    scanf("%s", input);
    // Validate the IEEE 754 representation
    char correct_answer[35];
    strcpy(correct_answer, float_to_ieee754(num)); //calculates the correct IEEE754 representation
    if (strcmp(input, correct_answer) == 0) {
        printf("Correct!\n");
    } else {
        printf("Incorrect. The correct answer is: %s\n", correct_answer);
    }
}
int main(){
    srand(time(NULL));
    printf("What do you want to practice? (1: hex, 2: binary, 3: IEEE 754): ");
    int choice;
    scanf("%d", &choice);
    if (choice == 1) {
        hex();
    } else if (choice == 2) {
        binary();
    } else if (choice == 3) {
        IEEE754();
    } else {
        printf("Invalid choice.\n");
    }
    return 0;
}
