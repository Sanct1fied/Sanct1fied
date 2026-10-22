void main() {
    String item1, item2, item3;
    double cost1, cost2, cost3;
    Scanner sc = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);
    item1 = "Monster";
    item2 = "Crisps";
    item3 = "Spell";
    cost1 = 1.5;
    cost2 = 2.5;
    cost3 = 3.5;
    System.out.println("Please insert coins.:");
    double balance = sc.nextDouble();
    System.out.println("What item do you want?\nitem1="+item1+"\nitem2="+item2+"\nitem3="+item3);
    String chosenItem = sc2.nextLine();
    if (chosenItem.equals("item1")){
        if (balance >= cost1){System.out.println("Here you go. your change is £" + (balance - cost1));}
        else {System.out.println("Not enough coins");}
    }
    else if  (chosenItem.equals("item2")){
        if (balance >= cost2){System.out.println("Here you go. your change is £" + (balance - cost2));}
        else {System.out.println("Not enough coins");}
    }
    else if (chosenItem.equals("item3")){
        if (balance >= cost3) {System.out.println("Here you go. your change is £" + (balance - cost3));}
        else {System.out.println("Not enough coins");}
    }
    else {System.out.println("Invalid choice.");}
}
