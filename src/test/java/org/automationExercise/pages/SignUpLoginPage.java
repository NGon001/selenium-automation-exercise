package org.automationExercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SignUpLoginPage extends BasePage {
    public SignUpLoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver,wait);
    }

    public String signUpText = "New User Signup!";
    public String signUpFullFormText = "Enter Account Information";
    public String signUpCreateAccountButtonText = "Create Account";
    public String signUpSuccessMessage = "Account Created!";
    public String signUpContinueButtonText = "Continue";
    public String loginAccountText = "Login to your account";
    //public By signUpFormLocator = By.cssSelector("form[action=\"/signup\"]");
    public By nameInputSigUpFormLocator = By.cssSelector("input[data-qa=\"signup-name\"]");
    public By emailInputSigUpFormLocator = By.cssSelector("input[data-qa=\"signup-email\"]");
    public By sigunUpButtonLocator = By.cssSelector("button[data-qa=\"signup-button\"]");
    public By signUpNameLocator = By.cssSelector("input[data-qa=\"name\"]");
    public By signUpEmailLocator = By.cssSelector("input[data-qa=\"email\"]");
    public By signUpPasswordLocator = By.cssSelector("input[data-qa=\"password\"]");
    public By signUpBirthDayLocator = By.cssSelector("select[data-qa=\"days\"]");
    public By signUpBirthMonthLocator = By.cssSelector("select[data-qa=\"months\"]");
    public By signUpBirthYearLocator = By.cssSelector("select[data-qa=\"years\"]");
    public By signUpFirstNameLocator = By.cssSelector("input[data-qa=\"first_name\"]");
    public By signUpLastNameLocator = By.cssSelector("input[data-qa=\"last_name\"]");
    public By signUpCompanyLocator = By.cssSelector("input[data-qa=\"company\"]");
    public By signUpAddress1Locator = By.cssSelector("input[data-qa=\"address\"]");
    public By signUpAddress2Locator = By.cssSelector("input[data-qa=\"address2\"]");
    public By signUpCountryLocator = By.cssSelector("select[data-qa=\"country\"]");
    public By signUpStateLocator = By.cssSelector("input[data-qa=\"state\"]");
    public By signUpCityLocator = By.cssSelector("input[data-qa=\"city\"]");
    public By signUpZipCodeLocator = By.cssSelector("input[data-qa=\"zipcode\"]");
    public By signUpPhoneNumberLocator = By.cssSelector("input[data-qa=\"mobile_number\"]");
    public By loginEmailInputLocator = By.cssSelector("input[data-qa=\"login-email\"]");
    public By loginPasswordInputLocator = By.cssSelector("input[data-qa=\"login-password\"]");
    public String loginButtonText = "Login";
    public String homeButtonText = "Home";
    public String signUpEmailAlreadyExistErrorMessage = "Email Address already exist!";
    public String loginErrorIncorrectDataMessage = "Your email or password is incorrect!";
    public By navigationBarSignUpLogin = By.cssSelector("ul[class=\"nav navbar-nav\"] a[href=\"/login\"]");

    public void verifySignUpPageLoaded(){
        WebElement signUpTextLocator = getByText(this.signUpText);
        Assert.assertTrue(signUpTextLocator.isDisplayed());
    }

    public void fillShortSigUpForm(String Name, String email){
        WebElement nameInput = wait(this.nameInputSigUpFormLocator);
        nameInput.sendKeys(Name);

        WebElement emailInput = wait(this.emailInputSigUpFormLocator);
        emailInput.sendKeys(email);
    }

    public void clickSignUpButton(){
        WebElement signUpButtonLocator = wait(this.sigunUpButtonLocator);
        signUpButtonLocator.click();
    }

    public void verefieSignUpFullFormLoaded(){
        WebElement signUpTextLocator = getByText(this.signUpFullFormText);
        Assert.assertTrue(signUpTextLocator.isDisplayed());
    }

    public void selectBirthDay(String birthDay, String birthMonth, String birthYear){
        Select signUpBirthDayInput = getSelectFromElement(wait(this.signUpBirthDayLocator));
        signUpBirthDayInput.selectByVisibleText(birthDay);
        Select signUpBirthMonthInput = getSelectFromElement(wait(this.signUpBirthMonthLocator));
        signUpBirthMonthInput.selectByVisibleText(birthMonth);
        Select signUpBirthYearInput = getSelectFromElement(wait(this.signUpBirthYearLocator));
        signUpBirthYearInput.selectByVisibleText(birthYear);
    }

    public void fillAddressInformation(String Name, String Company, String Address1, String Address2, String Country, String City, String State, String ZipCode, String phoneNumber){
        String firstName = Name.split(" ")[0];
        String lastName = Name.split(" ")[1];
        WebElement signUpFirstNameInput = wait(this.signUpFirstNameLocator);
        signUpFirstNameInput.sendKeys(firstName);
        WebElement signUpLastNameInput = wait(this.signUpLastNameLocator);
        signUpLastNameInput.sendKeys(lastName);
        WebElement signUpCompanyInput = wait(this.signUpCompanyLocator);
        signUpCompanyInput.sendKeys(Company);
        WebElement signUpAddress1Input = wait(this.signUpAddress1Locator);
        signUpAddress1Input.sendKeys(Address1);
        WebElement signUpAddress2Input = wait(this.signUpAddress2Locator);
        signUpAddress2Input.sendKeys(Address2);
        Select signUpCountryInput = getSelectFromElement(wait(this.signUpCountryLocator));
        signUpCountryInput.selectByVisibleText(Country);
        WebElement signUpStateInput = wait(this.signUpStateLocator);
        signUpStateInput.sendKeys(State);
        WebElement signUpCityInput = wait(this.signUpCityLocator);
        signUpCityInput.sendKeys(City);
        WebElement signUpZipCodeInput = wait(this.signUpZipCodeLocator);
        signUpZipCodeInput.sendKeys(ZipCode);
        WebElement signUpPhoneNumberInput = wait(this.signUpPhoneNumberLocator);
        signUpPhoneNumberInput.sendKeys(phoneNumber);
    }

    public void verifySuccessSignUpPageLoaded(){
        WebElement successMessage = getByTextContains(this.signUpSuccessMessage);
        Assert.assertTrue(successMessage.isDisplayed());
    }

    public void fillLoginSignUpForm(String Title, String Name, String email, String password, String birthDay, String birthMonth, String birthYear, String Company, String Address1, String Address2, String Country, String State, String City, String ZipCode, String phoneNumber){
        //Title
        WebElement radio = getRadioByText(Title);
        Assert.assertTrue(radio.isDisplayed());
        radio.click();

        //Name (Name should be autofill from short form)
        WebElement nameInput = wait(this.signUpNameLocator);
        Assert.assertEquals(getElementValue(nameInput), Name);

        //Email (Email should be autofill from short form)
        WebElement emailInput = wait(this.signUpEmailLocator);
        Assert.assertEquals(getElementValue(emailInput), email);
        Assert.assertEquals(emailInput.getAttribute("disabled"), "true");

        //Password
        WebElement passwordInput = wait(this.signUpPasswordLocator);
        passwordInput.sendKeys(password);
        Assert.assertEquals(getElementValue(passwordInput), password);

        //Birth Date
        this.selectBirthDay(birthDay, birthMonth, birthYear);

        //Address Information
        this.fillAddressInformation(Name, Company, Address1, Address2, Country, City, State, ZipCode, phoneNumber);

        //Click Create Account button
        WebElement signUpCreateAccountButton = getButtonByText(this.signUpCreateAccountButtonText);
        signUpCreateAccountButton.click();

        this.verifySuccessSignUpPageLoaded();
    }

    public HomePage clickContinueButton(){
        WebElement continueButton = getLinkByText(this.signUpContinueButtonText);
        continueButton.click();
        return new HomePage(driver,wait);
    }

    public void fillLogin(String email, String password){
        WebElement emailInput = wait(this.loginEmailInputLocator);
        WebElement passwordInput = wait(this.loginPasswordInputLocator);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
    }

    public HomePage clickLoginButton(){
        WebElement loginButton = getButtonByText(this.loginButtonText);
        loginButton.click();
        return new HomePage(driver,wait);
    }

    public void verifyLoginPageText(){
        WebElement loginText = getByTextContains(this.loginAccountText);
        Assert.assertTrue(loginText.isDisplayed());
    }

    public void verifyErrorIncorrectDataMessage (){
        WebElement errorMessage = getByTextContains(this.loginErrorIncorrectDataMessage);
        Assert.assertTrue(errorMessage.isDisplayed());
    }

    public void verifySignUpLoginButtonVisible(){
        WebElement signUpLoginButton = wait(this.navigationBarSignUpLogin);
        Assert.assertTrue(signUpLoginButton.isDisplayed());
        Assert.assertTrue(signUpLoginButton.isEnabled());
    }

    public HomePage clickHomeButton(){
        WebElement homeButton = getLinkByText(this.homeButtonText);
        homeButton.click();
        return new HomePage(driver,wait);
    }

    public void verifyEmailExistMessage(){
        WebElement errorMessage = getByTextContains(this.signUpEmailAlreadyExistErrorMessage);
        Assert.assertTrue(errorMessage.isEnabled());
    }
}
