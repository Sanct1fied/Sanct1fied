import java.util.Scanner;

void main() {
    // 9780306406157
    // 9780140266900
    // 9780769221076
    // 9781101487280
    // 9780306406153 ?!
    System.out.println("What is your ISBN13 number?");
    Scanner sc  = new Scanner(System.in);
    String isbn = sc.nextLine();
    String checkDigit = String.valueOf(isbn.charAt(12));
    double checkDigit2 = Double.parseDouble(checkDigit);
    double sum = 0;
    for (int i = 1; i <= 12; i++) {
        String first = String.valueOf((isbn.charAt(i-1)));
        double first2 = Double.parseDouble(first);

        if ((i-1)%2 != 0) first2 = first2*3;

        sum +=  first2;
    }
    double remainder = sum % 10;
    double potential = 10 - remainder;
    if (potential == checkDigit2) System.out.println("This ISBN13 is correct.");
    else if (remainder == 0 && checkDigit2 == 0) System.out.println("This ISBN13 is correct.");
    else System.out.println("This ISBN13 is not correct.");

}
