public class Adder {
	public static void main( String[] args ){
		int myNumber;
		myNumber = Integer.parseInt(args[0]);
		int myNumber2;
		myNumber2 = Integer.parseInt(args[1]);
		int output;
		output = myNumber + myNumber2;
		System.out.println(output);
	}
}
