import java.util.ArrayList;

public class Design {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int i;
        ArrayList<Integer> floors = new ArrayList<Integer>();
        floors.add(-1);
        for (i = 1; i < N; i++) {
            if (numberChecker(i)){
                N++;
                continue;
            }
            floors.add(i);
        }
        for (int j = floors.size()-1; j > 0; j=j-2){
            System.out.print(floors.get(j-1));
            System.out.print("    ");
            System.out.println(floors.get(j));
        }
    }
    public static boolean numberChecker(int X){
        String number = String.valueOf(X);
        if (X == 13){return true;}
        if (X == 0) {return true;}
        char[] chars = number.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '4'){
                return true;
            }
        }
        return false;
    }
}
