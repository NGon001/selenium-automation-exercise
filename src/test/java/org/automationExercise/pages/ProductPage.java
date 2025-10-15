package org.automationExercise.pages;

import org.automationExercise.classes.ProductData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver, WebDriverWait wait, boolean verify) {
        super(driver, wait,verify);
    }

    private static final By productNameLocator = By.cssSelector(".product-information h2");
    private static final By productPriceLocator = By.cssSelector(".product-information span span");
    private static final By productQuantityLocator = By.cssSelector(".product-information #quantity");

    private static final String addToCartButtonText = "Add to cart";
    private static final String viewCartText = "View Cart";

    @Override
    protected void verifyPageLoaded() {
        String url = driver.getCurrentUrl();
        assert url != null;
        Assert.assertTrue(
                url.contains("product_details"),
                "Expected URL to contain 'product_details' but got: " + url
        );
    }

    public void verifyProductDetails(ProductData productData) {
        WebElement productNameElement = wait(productNameLocator);
        WebElement productPriceElement = wait(productPriceLocator);

        String productNameText = productNameElement.getText();
        double productPriceText = ProductData.textPriceToFloat(productPriceElement.getText());

        Assert.assertEquals(productNameText, productData.getName(), "Product name is incorrect");
        Assert.assertEquals(productPriceText, productData.getPrice(), "Product price is incorrect");
    }

    public void SetProductQuantity(int quantity){
        WebElement quantityElement = wait(productQuantityLocator);
        quantityElement.clear();
        quantityElement.sendKeys(String.valueOf(quantity));
    }

    public void clickAddToCartButton(){
        WebElement addToCartButton = getButtonByText(addToCartButtonText);
        addToCartButton.click();
    }

    public CartPage clickViewCart(){
        WebElement viewCartButton = getLinkByText(viewCartText);
        viewCartButton.click();
        return new CartPage(driver,wait,true);
    }

}
