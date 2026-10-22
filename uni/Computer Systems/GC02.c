#include<stdio.h>
#include<stdbool.h>

void twelvetotwentryfour(){
    int hour;
    int minute;
    int ampm;
    printf("what is the hour?");
    scanf("%d", &hour);
    printf("what is the minute?");
    scanf("%d", &minute);
    printf("is it 1. am 2. pm?");
    scanf("%d", &ampm);
    if (ampm==1) {
        printf("The time is %d:%02d\n", hour, minute);   
    } else if (ampm == 2){
        hour += 12;
        printf("The time is %d:%02d\n", hour, minute);   
    } else {
        printf("invalid input");
    }

}

void twentyfourtotwelve(){
    int hour;
    int minute;
    bool ampm;
    printf("what is the hour?");
    scanf("%d", &hour);
    printf("what is the minute?");
    scanf("%d", &minute);
    if (hour > 12){
        hour -= 12;
        printf("The time is %d:%02dpm\n", hour, minute); 
    } else {
        printf("The time is %d:%02dam\n", hour, minute); 
    }
}

int main(){
    printf("Which way do you want to convert?\n");
    printf("1. 12->24 2. 24->12");
    int choice;
    scanf("%d",&choice);
    if (choice == 1){
        twelvetotwentryfour();
    } else {
        twentyfourtotwelve();
    }
    return 0;
}
