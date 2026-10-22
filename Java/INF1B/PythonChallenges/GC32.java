import java.util.Random;
//import java.util.Scanner;
public class GC32 {
	static void main(String[] args) {
		String player_choice;
		player_choice = args[0];
		System.out.println(player_choice);
		Random rand = new Random();
		int n = rand.nextInt(2);
		System.out.println(n);
		if (n == 0 && player_choice.equals("Silent")) {System.out.println("You both go to prison for 1 year");}
		else if (n == 0 && player_choice == "Confess") {System.out.println("You are released from prison");}
		else if (n == 1 && player_choice == "Confess") {System.out.println("You both go to prison for 5 years");}
		else if (n == 1 && player_choice == "Silent") {System.out.println("You go to prison for 20 years");}
	}
}
