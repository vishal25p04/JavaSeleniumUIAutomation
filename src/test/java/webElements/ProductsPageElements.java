package webElements;

public class ProductsPageElements {
    public static String products = "//span[text()='Products']";
    public static String cartButton = "//a[contains(@class, 'shopping_cart')]";
    public static String addToCartButton (String productName){
        return "//div[text()='"+productName+"']/../../..//button[text()='Add to cart']";
    }


}
