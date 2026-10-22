import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CygnusMain {
    private static ArrayList<Store> stores = new ArrayList<>();

    static class Store {
        String name;
        ArrayList<Order> orders;
        Inventory inventory;

        public Store(String name) {
            this.name = name;
            this.orders = new ArrayList<>();
            this.inventory = new Inventory(0);
        }
    }

    static class Order {
        String orderID;
        String status;
        int price;
        ArrayList<OrderItems> items;
        ArrayList<Discount> discount;
        boolean dealapplied;
        String bestDiscountUsed;

        Order(String orderID, String status, int price) {
            this.orderID = orderID;
            this.status = status;
            this.price = price;
            items = new ArrayList<>();
            discount = new ArrayList<>();
            bestDiscountUsed = "";
        }
    }

    static class OrderItems {
        String productID;
        int quantity;

        OrderItems(String productID, int quantity) {
            this.productID = productID;
            this.quantity = quantity;
        }
    }

    static class Discount {
        String discountID;

        Discount(String discountID) {
            this.discountID = discountID;
        }
    }

    static class Inventory {
        int balance;
        ArrayList<product> products;
        HashMap<String, product> productMap;

        public Inventory(int balance) {
            this.balance = balance;
            this.products = new ArrayList<>();
            this.productMap = new HashMap<>();
        }

        public void addProduct(product p) {
            products.add(p);
            productMap.put(p.id, p);
        }
    }

    static class product {
        String id;
        String name;
        String type;
        String promoCode;
        String price;
        String stock;

        public product(String id, String name, String price, String type, String promoCode, String stock) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.promoCode = promoCode;
            this.price = price;
            this.stock = stock;
        }
    }

    public static String RemoveLeadingZeros(String temp) {
        temp = String.valueOf(Integer.parseInt(temp));
        return temp;
    }

    static Order currentOrder = null;
    static Store currentStore = null;
    static ArrayList<String> allorderids = new ArrayList<>();
    static ArrayList<String> productIDs = new ArrayList<>();
    static boolean error = false;

    public static void ReadInput(String line) {
        ArrayList<String> validDiscounts = new ArrayList<>();
        validDiscounts.add("HEALTH"); validDiscounts.add("UNI");
        validDiscounts.add("EMPLOYEE"); validDiscounts.add("PROMO");
        validDiscounts.add("VET");  validDiscounts.add("LOYALTY");
        validDiscounts.add("STAFF");

        if (line.isEmpty() || line.charAt(0) == '#') {
        } else {
            String[] inputs = line.split("\\|");
            if (inputs[0].startsWith("ORDER")) {
                String orderID = inputs[1];
                if (allorderids.contains(orderID)) {
                    error = true;
                } else {
                    allorderids.add(orderID);
                }
                String status = "rejected";
                int price = 0;
                currentOrder = (new Order(orderID, status, price));
                currentStore.orders.add(currentOrder);
            } else if (inputs[0].startsWith("ENDORDER")) {
                currentOrder = null;
            } else if (inputs[0].contains("ITEM")) {
                String productID = inputs[1];
                int quantity = Integer.parseInt(inputs[2]);
                currentOrder.items.add(new OrderItems(productID, quantity));
            } else if (inputs[0].contains("PRODUCT")) {
                if (productIDs.contains(inputs[1])) {
                    error = true;
                } else if (Character.isLowerCase(inputs[1].charAt(0))) {
                    error = true;
                } else productIDs.add(inputs[1]);
                product p = (new product(inputs[1], inputs[2], RemoveLeadingZeros(inputs[3]), inputs[4], inputs[5], RemoveLeadingZeros(inputs[6])));
                currentStore.inventory.addProduct(p);
            } else if (inputs[0].equals("INVENTORY")) {
                currentStore.inventory.balance = Integer.parseInt(inputs[1]);
            } else if (inputs[0].equals("STORE")) {
                currentStore = new Store(inputs[1]);
                stores.add(currentStore);
                if (currentStore.name.equals("GreenGrocer")){
                    discountMap.put("LOYALTY",0.17);
                }
                else if (currentStore.name.equals("PawPantry")){
                    discountMap.put("LOYALTY",0.09);
                }
            } else if (line.startsWith("DISCOUNT")) {
                String code = inputs[1];
                if (!validDiscounts.contains(code) && !code.isEmpty()) {
                    error = true;
                    System.out.println("Invalid discount code: " + code);
                }
                currentOrder.discount.add(new Discount(code));
            }
        }
    }

    public static void SortProducts() {
        for (Store store : stores) {
            store.inventory.products.sort(
                    Comparator.comparing(p -> p.id)
            );
        }
    }

    public static ArrayList<OrderItems> SortItems(Order order) {
        HashMap<String, Integer> combined = new HashMap<>();
        for (OrderItems item : order.items) {
            combined.put(item.productID, combined.getOrDefault(item.productID, 0) + item.quantity);
        }
        ArrayList<OrderItems> mergedItems = new ArrayList<>();
        for (String productID : combined.keySet()) {
            int qty = combined.get(productID);
            mergedItems.add(new OrderItems(productID, qty));
        }
        mergedItems.sort(Comparator.comparing(item -> item.productID));
        return mergedItems;
    }

    public static ArrayList<Discount> SortDiscounts(Order order) {

        HashMap<String, Discount> unique = new HashMap<>();

        for (Discount d : order.discount) {

            // keeps only one Discount per ID (overwrites duplicates)
            unique.put(d.discountID, d);
        }

        ArrayList<Discount> result =
                new ArrayList<>(unique.values());

        result.sort(Comparator.comparing(d -> d.discountID));

        return result;
    }

    public static boolean Validation(Store store) {
        ArrayList<String> product_types = new ArrayList<>();
        if (store.name.equals("WordWorld")){
        product_types.add("book");
        product_types.add("game");
        product_types.add("children");
        product_types.add("stationery");}
        else if (store.name.equals("PawPantry")){
            product_types.add("food");
            product_types.add("toy");
            product_types.add("accessory");
            product_types.add("grooming");
        }
        else if (store.name.equals("GreenGrocer")){
            product_types.add("produce");
            product_types.add("dairy");
            product_types.add("alcohol");
            product_types.add("bakery");
            product_types.add("pantry");
        }
        ArrayList<String> promotionCodes = new ArrayList<>();
        promotionCodes.add("r");
        if (store.name.equals("WordWorld")){
        promotionCodes.add("_"); promotionCodes.add("hr");
        promotionCodes.add("c");
        promotionCodes.add("f");
        promotionCodes.add("h");
        promotionCodes.add("t");}
        else if (store.name.equals("PawPantry")){
            promotionCodes.add("_");
            promotionCodes.add("b");
            promotionCodes.add("k");
            promotionCodes.add("z");
            promotionCodes.add("s");
        }
        else if (store.name.equals("GreenGrocer")){
            promotionCodes.add("_");
            promotionCodes.add("a");
            promotionCodes.add("j");
            promotionCodes.add("m");
            promotionCodes.add("y");
        }
        boolean valid_balance = store.inventory.balance >= 0;
        boolean valid_type = true; //assume valid until unvalidated
        boolean valid_promotion = true;
        boolean valid_price = true;
        boolean valid_stock = true;
        boolean valid_inventory = true;
        boolean valid_items = true;
        boolean valid_item_q = true;
        for (product product : store.inventory.products) {
            valid_type = product_types.contains(product.type);
            valid_promotion = promotionCodes.contains(product.promoCode);
            valid_stock = Integer.parseInt(product.stock) > 0;
            valid_price = Integer.parseInt(product.price) > 0;
            if (!(valid_type && valid_promotion && valid_stock && valid_price)) {
                valid_inventory = false;
            }
            for (Order order : store.orders) {
                for (OrderItems item : order.items) {

                    product p = store.inventory.productMap.get(item.productID);
                    valid_item_q = item.quantity > 0;
                    if (p == null || !valid_item_q) {
                        valid_items = false;
                    }
                }
            }
        }
        return valid_inventory && valid_items;
    }

    public static boolean AdditionalAcceptance(Store store,Order order, HashMap<String, product> productMap) {
        if (store.name.equals("WordWorld")) {
            int count = 0;
            for (OrderItems item : order.items) {
                product p = productMap.get(item.productID);
                if (p.type.equals("children")) {
                    count += item.quantity;
                }
            }
            if (count > 3 || order.items.size() > 6) {
                return false;
            } else {
                return true;
            }
        }
        else if (store.name.equals("PawPantry")) {
            int count = 0;
            ArrayList<String> product_types = new ArrayList<>();
            for (OrderItems item : order.items) {
                product p = productMap.get(item.productID);
                if  (p.type.equals("grooming") || p.type.equals("food")) {
                    product_types.add(p.type);
                }
            }
            if ((product_types.contains("grooming") &&  product_types.contains("food"))||order.items.size() > 3) {
                return false;
            }
        }
        else if (store.name.equals("GreenGrocer")) {
            int count = 0;
            for  (OrderItems item : order.items) {
                count += item.quantity;
            }
            if  (order.items.size() > 2 || count > 29) {
                return false;
            }
        }
        return true;
    }

    public static void OrderCheckAgainstInventory(Store store) {
        Order currentorder = null;
        for (int i = 0; i < store.orders.size(); i++) {
            currentorder = store.orders.get(i);
            for (int j = 0; j < currentorder.items.size(); j++) {
                String ID = currentorder.items.get(j).productID;
                for (product product : store.inventory.products) {
                    if (ID.equals(product.id)) {
                        if (!(currentorder.items.get(j).quantity <= Integer.parseInt(product.stock))) {
                        } else if (!AdditionalAcceptance(store, currentorder, store.inventory.productMap)) {}
                        else {
                            currentorder.status = "accepted";
                            product.stock = String.valueOf(Integer.parseInt(product.stock) - currentorder.items.get(j).quantity);
                        }
                    }
                }
            }
        }
    }

    public static double DiscountCalculationHashMap(Order order, HashMap<String, product> productMap) {
        double total = 0;
        for (OrderItems orderItems : order.items) {
            product product = productMap.get(orderItems.productID);
            if (product == null) {
                continue;
            }
            int itemPrice = Integer.parseInt(product.price);
            double bestPrice = itemPrice;
            boolean discountUsed = false;
            if (order.discount != null && !product.promoCode.contains("r")) {
                for (Discount d : order.discount) {
                    Double percent = discountMap.get(d.discountID);
                    if (percent == null) continue;
                    double discounted = (int) (itemPrice * (1-percent));
                    System.out.println(discounted);
                    bestPrice = Math.min(bestPrice, discounted);
                    order.bestDiscountUsed = d.discountID;
                    discountUsed = true;
                }
            }
            total += bestPrice * orderItems.quantity;
        }
        return total;
    }

    public static void PriceCalculation(Store store, HashMap<String, product> productMap) {
        int promoprice = 0;
        int discountprice = 0;
        for (Order order : store.orders) {
            promoprice = (int) Math.ceil(ExtraPromo(store, order, productMap));
            discountprice = (int) Math.ceil(DiscountCalculationHashMap(order, productMap));
            if (promoprice == 0 || discountprice == 0) {
                order.price = Math.max(promoprice, discountprice);
                if (Objects.equals(order.status, "accepted")) {
                    store.inventory.balance += order.price;
                }
            } else {
                order.price = Math.min(promoprice, discountprice);
                if (Objects.equals(order.status, "accepted")) {
                    store.inventory.balance += order.price;
                }
            }
        }
    }

    public static double ExtraPromo(Store store, Order order, HashMap<String, product> productMap) {
        ArrayList<Integer> eligibleItems = new ArrayList<>();
        ArrayList<Integer> eligibleT = new ArrayList<>();
        double total = 0;
        if (store.name.equals("PawPantry")){
            for (OrderItems orderItems : order.items) {
                product product = productMap.get(orderItems.productID);
                if (product == null) continue;
                int itemPrice = Integer.parseInt(product.price);
                if (product.promoCode.equals("k")) {
                    for (int i = 0; i < orderItems.quantity; i++) {
                        eligibleItems.add(itemPrice);}
                        if (eligibleItems.size() >= 2) {
                            for (Integer items : eligibleItems) {
                                total += (int) (itemPrice * (1-0.09));
                            }
                        }
                    }
                else if (product.promoCode.equals("s")) {
                    eligibleItems.add(itemPrice);
                    System.out.println(eligibleItems);
                    if (eligibleItems.size() >= 2) {
                        int cheapest = Collections.min(eligibleItems);
                        for (int i = 0; i < eligibleItems.size(); i++) {
                            total += eligibleItems.get(i); //add total excluding free items
                        }
                        total -= cheapest;
                    }
                    else total += itemPrice;
                }
                else if (product.type.equals("food") && product.promoCode.equals("z")) {
                    total += (int) (itemPrice * (1-0.14));
                }
                else {
                    total += itemPrice * orderItems.quantity;
                }
            }
        }
        else if (store.name.equals("WordWorld")){
            for (OrderItems orderItems : order.items) {
                product product = productMap.get(orderItems.productID);
                if (product == null) {
                    continue;
                }
                int itemPrice = Integer.parseInt(product.price);
                if (product.promoCode.equals("t")){
                    for (int i = 0; i < orderItems.quantity; i++) {
                        eligibleT.add(itemPrice);}
                    if (eligibleT.size() >= 2) {
                        for (Integer items : eligibleT) {
                            total += (int) (itemPrice * (1-0.18));
                        }
                    }
                    else total += itemPrice * orderItems.quantity;
                }
                else if (product.promoCode.equals("c")){
                    for (int i = 0; i < orderItems.quantity; i++) {
                        eligibleItems.add(itemPrice);}
                    eligibleItems.sort(Collections.reverseOrder()); //sort
                    for (int i = 0; i < eligibleItems.size(); i++) {
                        if ((i + 1) % 3 == 0) { //free items
                            order.dealapplied = true;
                            continue;
                        }
                        total += eligibleItems.get(i); //add total excluding free items
                    }
                }
                else if (product.promoCode.contains("h") && product.type.equals("book")) {
                    total += (int) Math.ceil((itemPrice * 0.95));
                    System.out.println(total);
                }
                else {
                    total += itemPrice * orderItems.quantity;
                }
            }
        }
        else if (store.name.equals("GreenGrocer")){
            for (OrderItems orderItems : order.items) {
                product product = productMap.get(orderItems.productID);
                if (product == null) {
                    continue;
                }
                int itemPrice = Integer.parseInt(product.price);
                if (product.promoCode.equals("a")) {
                    eligibleItems.add(itemPrice);
                    System.out.println(eligibleItems);
                    if (eligibleItems.size() >= 2) {
                        int cheapest = Collections.min(eligibleItems);
                        for (int i = 0; i < eligibleItems.size(); i++) {
                            total += eligibleItems.get(i); //add total excluding free items
                        }
                        total -= cheapest;
                    }
                    else total += itemPrice;
                }
                else if (product.promoCode.equals("m") && product.type.equals("produce")) {
                    total += (itemPrice * (1-0.10))* orderItems.quantity;
                }
                else if (product.promoCode.equals("y")) {
                    eligibleItems.add(itemPrice);
                    if  (eligibleItems.size() >= 2) {
                        total += (itemPrice *(1-0.07))* orderItems.quantity;
                    }
                    else total += itemPrice * orderItems.quantity;
                }
                else if (product.promoCode.equals("j")) {
                    eligibleItems.add(itemPrice);
                    ArrayList<String> product_types  = new ArrayList<>();
                    product_types.add(product.type);
                    if (product_types.containsAll(Arrays.asList("dairy","produce","alcohol"))){
                        total = total - 4;
                    }

                }
            }

        }
        return total;
    }

    /*public static double Promotion(Order order, HashMap<String, product> productMap){
        ArrayList<Integer> eligibleItems = new ArrayList<>();

    }*/
    //implement product discount code h
    //implement ok if no orders present
    static HashMap<String, Double> discountMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        stores.clear(); allorderids.clear(); error = false; productIDs.clear();
        currentStore = null; currentOrder = null; discountMap.clear();
        discountMap.put("UNI", 0.10); discountMap.put("PROMO", 0.07); discountMap.put("VET",0.19);
        discountMap.put("HEALTH", 0.20); discountMap.put("EMPLOYEE", 0.17); discountMap.put("STAFF",0.23);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        boolean storeRecordPresent = false;
        while ((line = reader.readLine()) != null) {
            if (line.contains("STORE")) {
                storeRecordPresent = true;
            } else if (storeRecordPresent || line.contains("#")) {
            } else {
                System.out.println("ERROR");
                break;
            }
            line = line.trim();
            if (line.isEmpty()) continue;
            ReadInput(line);
            if ((line.equalsIgnoreCase("ENDORDER") || line.equalsIgnoreCase("ENDINVENTORY")) && reader.ready() == false)
                break;
        }
        if ((line == null) || (line.isEmpty()) || error) {
            System.out.println("ERROR");
            error = true;
        }
        SortProducts();
        for (Store store : stores) {
            if (!Validation(store)) {
                System.out.println("ERROR");
                error = true;
            }
            OrderCheckAgainstInventory(store);
            for (Order order : store.orders) {
                order.items = SortItems(order);
                order.discount = SortDiscounts(order);
                DiscountCalculationHashMap(order, store.inventory.productMap);
            }
            PriceCalculation(store, store.inventory.productMap);
        }
        for (Store store : stores) {
            if (Validation(store) && !error) {
                Print(store);
            }
        }

    }

    public static void Print(Store store) {
        System.out.println("STORE|" + store.name + "\n");
        for (Order order : store.orders) {
            System.out.print("ORDER|" + order.orderID);
            System.out.print("|" + order.status);
            System.out.println("|" + order.price);
            for (OrderItems item : order.items) {
                System.out.print("ITEM|" + item.productID);
                System.out.println("|" + item.quantity);
            }
            if (order.bestDiscountUsed != "") {
                System.out.println("DISCOUNT|" + order.bestDiscountUsed);
            }
            System.out.println("ENDORDER\n");
        }
        System.out.println("INVENTORY|" + store.inventory.balance);
        for (product product : store.inventory.products) {
            System.out.print("PRODUCT");
            System.out.print("|" + product.id);
            System.out.print("|" + product.name);
            System.out.print("|" + product.price);
            System.out.print("|" + product.type);
            System.out.print("|" + product.promoCode);
            System.out.println("|" + product.stock);
        }
        System.out.println("ENDINVENTORY\n");
    }
}
