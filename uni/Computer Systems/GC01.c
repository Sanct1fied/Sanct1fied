#include<stdio.h>
#include<time.h>
#include<stdbool.h>
#include<stdlib.h>

int randint(int min, int max) {
    return rand() % (max - min + 1) + min;
}

int main(){
    srand(time(NULL));
    int number = randint(0,100);
    int guess;
    bool guessed = false;
    do {
    printf("Make a guess:");
    scanf("%d",&guess);
    if (number == guess){
        printf("Congratulations! You guessed it right.");
        guessed = true;
    } else if (number > guess){
        printf("You guessed too low");
    } else {
        printf("You guessed too high!");
    }
    }   while (guessed == false);
    return 0;
}
