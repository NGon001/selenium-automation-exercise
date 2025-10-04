package org.automationExercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public WebElement wait(By locator){
        WebElement element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Assert.assertTrue(element.isDisplayed());
        return element;
    }

    public WebElement getByText(String text){
        return this.wait(By.xpath("//*[text()='" + text + "']"));
    }

    public WebElement getByTextContains(String text){
        return this.wait(By.xpath("//*[contains(text(),'" + text + "')]"));
    }

    public WebElement getRadioByText(String text){
        return this.wait(By.xpath("//label[.//input[@type='radio'] and contains(normalize-space(.),'" + text + "')]"));
    }

    public String getElementValue (WebElement element){
        return element.getAttribute("value");
    }

    public Select getSelectFromElement(WebElement element){
        Assert.assertTrue(element.isDisplayed());
        return new Select(element);
    }

    public WebElement getButtonByText(String text){
        return this.wait(By.xpath("//button[contains(normalize-space(.), '"+ text + "')]"));
    }

    public WebElement getLinkByText(String text){
        return this.wait(By.xpath("//a[contains(normalize-space(.), '"+ text + "')]"));
    }
}
