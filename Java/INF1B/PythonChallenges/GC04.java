import java.util.Scanner;
void main() {
    Scanner sc = new Scanner(System.in);
    System.out.print("how big do you want your pyramid to be?");
    int pyramid = sc.nextInt();
    String x = "x";
    for  (int i = 1; i <= pyramid; i++) {

        System.out.print(x);
        System.out.print("\n");
        x = x + "x" + "x";
    }

}
