package org.automationExercise.pages;

import org.automationExercise.classes.ProductData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class ProductsPage extends BasePage {
    public ProductsPage(WebDriver driver, WebDriverWait wait,boolean verify) {
        super(driver, wait,verify);
    }

    private static final String allProductsText = "All Products";
    private static final By productsLocator = By.cssSelector(".features_items .col-sm-4");
    private static final By productImageLocator = By.cssSelector("img");
    private static final By productOverlayAddToCartButtonLocator = By.cssSelector(".product-overlay a");
    private static final By productPriceLocator = By.cssSelector("h2");
    private static final By productNameLocator = By.cssSelector("p");
    private static final String productAddedToCartText = "Your product has been added to cart.";
    private static final String continueShoppingButtonText = "Continue Shopping";
    private static final String viewCartText = "View Cart";

    @Override
    protected void verifyPageLoaded(){
        WebElement allProductsTextElement = getByText(allProductsText);
        Assert.assertTrue(allProductsTextElement.isDisplayed());
    }

    public List<WebElement> verifyProductsExist(){
        List<WebElement> products = getElements(productsLocator);
        Assert.assertFalse(products.isEmpty(), "No products found");
        return products;
    }

    public ProductData addProductToCartByIndex(int index) throws InterruptedException {
        List<WebElement> products = this.verifyProductsExist();
        Assert.assertTrue(index >= 0 && index < products.size(), "Invalid product index");
        WebElement product = products.get(index);

        String productName = waitByElement(product,productNameLocator).getText();
        double productPrice = ProductData.textPriceToFloat(waitByElement(product,productPriceLocator).getText());

        WebElement image = waitByElement(product,productImageLocator);
        Assert.assertTrue(image.isDisplayed() && image.isEnabled(), "Image is not displayed");

        ScrollToElement(product);
        Thread.sleep(300);
        hoverToElement(product);
        Thread.sleep(600);
        WebElement addToCartButton = waitByElement(product,productOverlayAddToCartButtonLocator);
        Assert.assertTrue(addToCartButton.isDisplayed() && addToCartButton.isEnabled(), "Add to cart button is not displayed");
        hoverToElement(addToCartButton);
        addToCartButton.click();

        getByTextContains(productAddedToCartText);

        return new ProductData(productName,productPrice);
    }

    public CartPage clickViewCart(){
        WebElement viewCartButton = getLinkByText(viewCartText);
        viewCartButton.click();
        return new CartPage(driver,wait,true);
    }

    public void clickContinueShopping(){
        WebElement continueButton = getButtonByText(continueShoppingButtonText);
        continueButton.click();
    }
}
