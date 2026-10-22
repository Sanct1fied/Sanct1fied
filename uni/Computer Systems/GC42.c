#include<time.h>
#include<stdlib.h>
#include<stdio.h>

int randint(int min, int max) {
    return rand() % (max - min + 1) + min;
}

int main(){
    srand(time(NULL));
    int arr[50];
    for (int i = 0;i<50;i++){
        arr[i] = randint(0,100000);
    }
    int min; min = 100000; int max; max = 0;
    int sum;
    int length = sizeof(arr) / sizeof(arr[0]);
    for (int i = 0;i < length;i++){
        sum += arr[i];
        int cur = arr[i];
        if (cur < min){
            min = cur;
        }
        if (cur > max){
            max = cur;
        }
    }
    int mean = sum/50;
    printf("Max:%d", max);
    printf("Min:%d", min);
    printf("Mean:%d",mean);
}
