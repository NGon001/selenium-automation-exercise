package org.automationExercise.pages;

import org.automationExercise.classes.ProductData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver, WebDriverWait wait, boolean verify) {
        super(driver, wait,verify);
    }

    private static final String shoppingCartText = "Shopping Cart";
    private static final By productsListLocator = By.cssSelector("#cart_info_table tbody tr");
    private static final By productNameLocator = By.cssSelector("h4");
    private static final By productPriceLocator = By.cssSelector(".cart_price p");
    private static final By productQuantityLocator = By.cssSelector(".cart_quantity button");

    @Override
    protected void verifyPageLoaded(){
        WebElement shoppingCart = getByText(shoppingCartText);
        Assert.assertTrue(shoppingCart.isDisplayed());
    }

    public List<WebElement> verifyProductsExist(){
        List<WebElement> products = getElements(productsListLocator);
        Assert.assertFalse(products.isEmpty(), "No products found");
        return products;
    }

    public void verifyProductAddedToCart(String Name, double Price, int Quantity, int productIndex){
        List<WebElement> products = verifyProductsExist();
        WebElement product = products.get(productIndex);
        String productName = waitByElement(product, productNameLocator).getText();
        double productPrice = ProductData.textPriceToFloat(waitByElement(product, productPriceLocator).getText());
        int productQuantity = Integer.parseInt(waitByElement(product, productQuantityLocator).getText());

        Assert.assertEquals(productName, Name, "Product name is incorrect");
        Assert.assertEquals(productPrice, Price, "Product price is incorrect");
        Assert.assertEquals(productQuantity, Quantity, "Product quantity is incorrect");
    }

}
