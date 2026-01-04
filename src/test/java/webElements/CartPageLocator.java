package webElements;

public class CartPageLocator {

    public static String itemName (String productName){
        return "//div[text()='"+productName+"']";
    }
    public static String checkoutButton = "//button[text()='Checkout']";
}
