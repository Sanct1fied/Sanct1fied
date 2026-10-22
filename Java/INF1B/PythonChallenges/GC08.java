import java.util.Scanner;
void main() {
    Scanner sc = new Scanner(System.in);
    System.out.println("What is your height in meters?:");
    double height = sc.nextDouble();
    System.out.println("What is your weight in kilograms?:");
    double weight = sc.nextDouble();
    double bmi = weight / (height * height);
    System.out.println(bmi);
    if (bmi < 18.5) System.out.println("You are underweight.");
    else if (bmi > 18.5 && bmi < 25) System.out.println("You are a normal weight.");
    else if (bmi > 25 && bmi < 30) {System.out.println("You are overweight.");}
    else if (bmi > 30) System.out.println("You are obese.");

}
