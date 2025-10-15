package org.automationExercise.tests;

import org.automationExercise.classes.ProductData;
import org.automationExercise.pages.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CartFunctionality extends BaseTest {
    HomePage homePage;
    ProductsPage productsPage;

    @BeforeMethod
    public void beforeTest(){
        homePage = new HomePage(driver,wait,true);
        productsPage = homePage.navigateToProductsPage(true);
    }

    /*
    Test Case 12: Add Products in Cart
    1. Launch browser
    2. Navigate to url 'http://automationexercise.com'
    3. Verify that home page is visible successfully
    4. Click 'Products' button
    5. Hover over first product and click 'Add to cart'
    6. Click 'Continue Shopping' button
    7. Hover over second product and click 'Add to cart'
    8. Click 'View Cart' button
    9. Verify both products are added to Cart
    10. Verify their prices, quantity and total price
    */

    @Test
    public void addProductToCart() throws InterruptedException {
        List<ProductData> productDatas = new ArrayList<>();

        productDatas.add(productsPage.addProductToCartByIndex(0));
        productsPage.clickContinueShopping();

        productDatas.add(productsPage.addProductToCartByIndex(2));
        productsPage.clickContinueShopping();

        productDatas.add(productsPage.addProductToCartByIndex(3));
        CartPage cartPage = productsPage.clickViewCart();

        for (int i = 0; i < productDatas.size(); i++) {
            cartPage.verifyProductAddedToCart(productDatas.get(i).getName(), productDatas.get(i).getPrice(), 1, i);
        }
    }

    @Test
    public void verifyProductQuantityInside(){
        int productIndex = 0;
        int productQuantity = 4;
        ProductData productData = productsPage.getProductDataByIndex(productIndex);
        ProductPage productPage = productsPage.clickViewProductByIndex(productIndex);
        productPage.verifyProductDetails(productData);
        productPage.SetProductQuantity(productQuantity);
        productPage.clickAddToCartButton();
        CartPage cartPage = productPage.clickViewCart();
        cartPage.verifyProductAddedToCart(productData.getName(), productData.getPrice(),productQuantity, productIndex);
    }
}
