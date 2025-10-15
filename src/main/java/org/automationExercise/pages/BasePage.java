package org.automationExercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait,boolean verify) {
        this.driver = driver;
        this.wait = wait;
        if(verify) verifyPageLoaded();
    }

    protected abstract void verifyPageLoaded();

    public WebElement wait(By locator){
        WebElement element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Assert.assertTrue(element.isDisplayed());
        return element;
    }

    public List<WebElement> getElements(By locator){
        wait(locator);
        List<WebElement> elements = driver.findElements(locator);
        Assert.assertFalse(elements.isEmpty(), "No elements found for locator: " + locator);
        return elements;
    }

    public WebElement waitByElementAndLocator(WebElement parent, By childLocator) {
        WebElement child = this.wait.until(ExpectedConditions.visibilityOf(parent.findElement(childLocator)));
        Assert.assertTrue(child.isDisplayed(), "Child element is not visible: " + childLocator);
        return child;
    }

    public By getButtonLocator(String text){
        return By.xpath("//button[contains(normalize-space(.), '"+ text + "')]");
    }
    public By getLinkLocator(String text){
        return By.xpath("//a[contains(normalize-space(.), '"+ text + "')]");
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

    public void ScrollToElement(WebElement element){
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'auto',block:'start'});", element
        );
    }

    public void hoverToElement(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
}
