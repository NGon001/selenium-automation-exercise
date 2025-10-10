package org.automationExercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TestCasesPage extends BasePage {
    public TestCasesPage(WebDriver driver, WebDriverWait wait,boolean verify) {
        super(driver, wait,verify);
    }

    @Override
    protected void verifyPageLoaded() {
        WebElement testCaseTextLocator = getByTextContains(testCasesText);
        Assert.assertTrue(testCaseTextLocator.isDisplayed());
    }

    private static final String testCasesText = "Below is the list of test Cases for you to practice";
}
