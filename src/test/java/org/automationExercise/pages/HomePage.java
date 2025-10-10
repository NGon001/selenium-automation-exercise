package org.automationExercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver, WebDriverWait wait,boolean verify) {
        super(driver, wait,verify);
    }

    private static final By navigationBarTestCase = By.cssSelector("ul[class=\"nav navbar-nav\"] a[href=\"/test_cases\"]");
    private static final By navigationBarSignUpLogin = By.cssSelector("ul[class=\"nav navbar-nav\"] a[href=\"/login\"]");
    private static final String futureItemsText = "Features Items";
    private static final String deleteAccountButtonText = "Delete Account";
    private static final String logoutButtonText = "Logout";
    private static final String productsButtonText = "Products";
    private static final String LoggedInAsText = "Logged in as";
    private static final String accountDeletedMessage = "Account Deleted!";
    private static final String accountDeleteContinueButtonText = "Continue";

    @Override
    protected void verifyPageLoaded() {
        WebElement featureItemsTextLocaotr = getByText(futureItemsText);
        Assert.assertTrue(featureItemsTextLocaotr.isDisplayed());
    }

    public SignUpLoginPage navigateToSignUpLoginPage(boolean verify){
        WebElement signUpLoginButton = wait(navigationBarSignUpLogin);
        signUpLoginButton.click();
        return new SignUpLoginPage(driver,wait,verify);
    }

    public TestCasesPage navigateToTestCasesPage(boolean verify){
        WebElement testCasesButton = wait(navigationBarTestCase);
        testCasesButton.click();
        return new TestCasesPage(driver, wait,verify);
    }

    public ProductsPage navigateToProductsPage(boolean verify){
        getByTextContains(productsButtonText).click();
        return new ProductsPage(driver, wait,verify);
    }

    public void verifyLoggedInAs(String Name){
        WebElement loggedInAs = getByTextContains(LoggedInAsText);
        Assert.assertTrue(loggedInAs.isDisplayed());
        Assert.assertEquals(loggedInAs.getText(), "Logged in as " + Name);
    }

    public void clickDeleteAccountButton(){
        WebElement deleteAccountButton = getLinkByText(deleteAccountButtonText);
        deleteAccountButton.click();
    }

    public void verifyAccountDeletedMessage(){
        WebElement accountDeletedMessage = getByTextContains(HomePage.accountDeletedMessage);
        Assert.assertTrue(accountDeletedMessage.isDisplayed());
    }

    public void clickContinueButton(){
        WebElement continueButton = getLinkByText(accountDeleteContinueButtonText);
        continueButton.click();
        this.verifyPageLoaded();
    }

    public SignUpLoginPage clickLogoutButton(boolean verify){
        WebElement logoutButton = getLinkByText(logoutButtonText);
        logoutButton.click();
        return new SignUpLoginPage(driver,wait,verify);
    }
}
