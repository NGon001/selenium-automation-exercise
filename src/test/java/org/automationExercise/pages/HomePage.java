package org.automationExercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public By navigationBarTestCase = By.cssSelector("ul[class=\"nav navbar-nav\"] a[href=\"/test_cases\"]");
    public By navigationBarSignUpLogin = By.cssSelector("ul[class=\"nav navbar-nav\"] a[href=\"/login\"]");
    public String futureItemsText = "Features Items";
    public String deleteAccountButtonText = "Delete Account";
    public String logoutButtonText = "Logout";
    public String LoggedInAsText = "Logged in as";
    public String accountDeletedMessage = "Account Deleted!";
    public String accountDeleteContinueButtonText = "Continue";

    public void verifyHomePageLoad(){
        WebElement featureItemsTextLocaotr = getByText(this.futureItemsText);
        Assert.assertTrue(featureItemsTextLocaotr.isDisplayed());
    }

    public SignUpLoginPage navigateToSignUpLoginPage(){
        this.verifyHomePageLoad();
        WebElement signUpLoginButton = wait(this.navigationBarSignUpLogin);
        signUpLoginButton.click();
        return new SignUpLoginPage(driver,wait);
    }

    public TestCasesPage navigateToTestCasesPage(){
        this.verifyHomePageLoad();
        WebElement testCasesButton = wait(this.navigationBarTestCase);
        testCasesButton.click();
        return new TestCasesPage(driver, wait);
    }

    public void verifyLoggedInAs(String Name){
        WebElement loggedInAs = getByTextContains(this.LoggedInAsText);
        Assert.assertTrue(loggedInAs.isDisplayed());
        Assert.assertEquals(loggedInAs.getText(), "Logged in as " + Name);
    }

    public void clickDeleteAccountButton(){
        WebElement deleteAccountButton = getLinkByText(this.deleteAccountButtonText);
        deleteAccountButton.click();
    }

    public void verifyAccountDeletedMessage(){
        WebElement accountDeletedMessage = getByTextContains(this.accountDeletedMessage);
        Assert.assertTrue(accountDeletedMessage.isDisplayed());
    }

    public void clickContinueButton(){
        WebElement continueButton = getLinkByText(this.accountDeleteContinueButtonText);
        continueButton.click();
    }
}
