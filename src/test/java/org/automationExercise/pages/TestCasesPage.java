package org.automationExercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TestCasesPage extends BasePage {
    public TestCasesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String testCasesText = "Below is the list of test Cases for you to practice";

    public void verifyTestCasePageLoaded(){
        WebElement testCaseTextLocator = getByTextContains(this.testCasesText);
        Assert.assertTrue(testCaseTextLocator.isDisplayed());
    }
}
