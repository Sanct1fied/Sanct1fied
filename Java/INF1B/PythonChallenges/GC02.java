import java.util.Scanner;
void main() {
    Scanner sc = new Scanner(System.in);
    System.out.print("what hour is it? ");
    int hour = sc.nextInt();
    System.out.print("what minute is it? ");
    int minute = sc.nextInt();
    Scanner sc1 = new Scanner(System.in);
    System.out.print("is that 12 or 24 hour clock?:");
    String variant =  sc1.nextLine();
    if (variant.equals("12")) {
        Scanner sc2 = new Scanner(System.in);
        System.out.print("is that am or pm?:");
        String ampm = sc2.nextLine();
        if (ampm.equals("pm")) {
            hour += 12;
        }
        else {}
    }
    else if (variant.equals("24")) {
        if (hour > 12) {
            hour -= 12;
        }
    }
    System.out.println(hour + ":" + minute);
}
