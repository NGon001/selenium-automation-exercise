package org.automationExercise.tests;

import org.automationExercise.classes.ProductData;
import org.automationExercise.pages.BaseTest;
import org.automationExercise.pages.CartPage;
import org.automationExercise.pages.HomePage;
import org.automationExercise.pages.ProductsPage;
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
}
