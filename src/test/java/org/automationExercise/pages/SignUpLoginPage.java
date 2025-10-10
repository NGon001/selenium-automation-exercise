package org.automationExercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SignUpLoginPage extends BasePage {
    public SignUpLoginPage(WebDriver driver, WebDriverWait wait,boolean verify) {
        super(driver,wait,verify);
    }

    private static final String signUpText = "New User Signup!";
    private static final String signUpFullFormText = "Enter Account Information";
    private static final String signUpCreateAccountButtonText = "Create Account";
    private static final String signUpSuccessMessage = "Account Created!";
    private static final String signUpContinueButtonText = "Continue";
    private static final String loginAccountText = "Login to your account";
    //public By signUpFormLocator = By.cssSelector("form[action=\"/signup\"]");
    private static final By nameInputSigUpFormLocator = By.cssSelector("input[data-qa=\"signup-name\"]");
    private static final By emailInputSigUpFormLocator = By.cssSelector("input[data-qa=\"signup-email\"]");
    private static final By sigunUpButtonLocator = By.cssSelector("button[data-qa=\"signup-button\"]");
    private static final By signUpNameLocator = By.cssSelector("input[data-qa=\"name\"]");
    private static final By signUpEmailLocator = By.cssSelector("input[data-qa=\"email\"]");
    private static final By signUpPasswordLocator = By.cssSelector("input[data-qa=\"password\"]");
    private static final By signUpBirthDayLocator = By.cssSelector("select[data-qa=\"days\"]");
    private static final By signUpBirthMonthLocator = By.cssSelector("select[data-qa=\"months\"]");
    private static final By signUpBirthYearLocator = By.cssSelector("select[data-qa=\"years\"]");
    private static final By signUpFirstNameLocator = By.cssSelector("input[data-qa=\"first_name\"]");
    private static final By signUpLastNameLocator = By.cssSelector("input[data-qa=\"last_name\"]");
    private static final By signUpCompanyLocator = By.cssSelector("input[data-qa=\"company\"]");
    private static final By signUpAddress1Locator = By.cssSelector("input[data-qa=\"address\"]");
    private static final By signUpAddress2Locator = By.cssSelector("input[data-qa=\"address2\"]");
    private static final By signUpCountryLocator = By.cssSelector("select[data-qa=\"country\"]");
    private static final By signUpStateLocator = By.cssSelector("input[data-qa=\"state\"]");
    private static final By signUpCityLocator = By.cssSelector("input[data-qa=\"city\"]");
    private static final By signUpZipCodeLocator = By.cssSelector("input[data-qa=\"zipcode\"]");
    private static final By signUpPhoneNumberLocator = By.cssSelector("input[data-qa=\"mobile_number\"]");
    private static final By loginEmailInputLocator = By.cssSelector("input[data-qa=\"login-email\"]");
    private static final By loginPasswordInputLocator = By.cssSelector("input[data-qa=\"login-password\"]");
    private static final String loginButtonText = "Login";
    private static final String homeButtonText = "Home";
    private static final String signUpEmailAlreadyExistErrorMessage = "Email Address already exist!";
    private static final String loginErrorIncorrectDataMessage = "Your email or password is incorrect!";
    private static final By navigationBarSignUpLogin = By.cssSelector("ul[class=\"nav navbar-nav\"] a[href=\"/login\"]");

    @Override
    protected void verifyPageLoaded() {
        WebElement signUpTextLocator = getByText(signUpText);
        Assert.assertTrue(signUpTextLocator.isDisplayed());
    }

    public void fillShortSigUpForm(String Name, String email){
        WebElement nameInput = wait(nameInputSigUpFormLocator);
        nameInput.sendKeys(Name);

        WebElement emailInput = wait(emailInputSigUpFormLocator);
        emailInput.sendKeys(email);
    }

    public void clickSignUpButton(){
        WebElement signUpButtonLocator = wait(sigunUpButtonLocator);
        signUpButtonLocator.click();
    }

    public void verefieSignUpFullFormLoaded(){
        WebElement signUpTextLocator = getByText(signUpFullFormText);
        Assert.assertTrue(signUpTextLocator.isDisplayed());
    }

    public void selectBirthDay(String birthDay, String birthMonth, String birthYear){
        Select signUpBirthDayInput = getSelectFromElement(wait(signUpBirthDayLocator));
        signUpBirthDayInput.selectByVisibleText(birthDay);
        Select signUpBirthMonthInput = getSelectFromElement(wait(signUpBirthMonthLocator));
        signUpBirthMonthInput.selectByVisibleText(birthMonth);
        Select signUpBirthYearInput = getSelectFromElement(wait(signUpBirthYearLocator));
        signUpBirthYearInput.selectByVisibleText(birthYear);
    }

    public void fillAddressInformation(String Name, String Company, String Address1, String Address2, String Country, String City, String State, String ZipCode, String phoneNumber){
        String firstName = Name.split(" ")[0];
        String lastName = Name.split(" ")[1];
        WebElement signUpFirstNameInput = wait(signUpFirstNameLocator);
        signUpFirstNameInput.sendKeys(firstName);
        WebElement signUpLastNameInput = wait(signUpLastNameLocator);
        signUpLastNameInput.sendKeys(lastName);
        WebElement signUpCompanyInput = wait(signUpCompanyLocator);
        signUpCompanyInput.sendKeys(Company);
        WebElement signUpAddress1Input = wait(signUpAddress1Locator);
        signUpAddress1Input.sendKeys(Address1);
        WebElement signUpAddress2Input = wait(signUpAddress2Locator);
        signUpAddress2Input.sendKeys(Address2);
        Select signUpCountryInput = getSelectFromElement(wait(signUpCountryLocator));
        signUpCountryInput.selectByVisibleText(Country);
        WebElement signUpStateInput = wait(signUpStateLocator);
        signUpStateInput.sendKeys(State);
        WebElement signUpCityInput = wait(signUpCityLocator);
        signUpCityInput.sendKeys(City);
        WebElement signUpZipCodeInput = wait(signUpZipCodeLocator);
        signUpZipCodeInput.sendKeys(ZipCode);
        WebElement signUpPhoneNumberInput = wait(signUpPhoneNumberLocator);
        signUpPhoneNumberInput.sendKeys(phoneNumber);
    }

    public void verifySuccessSignUpPageLoaded(){
        WebElement successMessage = getByTextContains(signUpSuccessMessage);
        Assert.assertTrue(successMessage.isDisplayed());
    }

    public void fillLoginSignUpForm(String Title, String Name, String email, String password, String birthDay, String birthMonth, String birthYear, String Company, String Address1, String Address2, String Country, String State, String City, String ZipCode, String phoneNumber){
        //Title
        WebElement radio = getRadioByText(Title);
        Assert.assertTrue(radio.isDisplayed());
        radio.click();

        //Name (Name should be autofill from short form)
        WebElement nameInput = wait(signUpNameLocator);
        Assert.assertEquals(getElementValue(nameInput), Name);

        //Email (Email should be autofill from short form)
        WebElement emailInput = wait(signUpEmailLocator);
        Assert.assertEquals(getElementValue(emailInput), email);
        Assert.assertEquals(emailInput.getAttribute("disabled"), "true");

        //Password
        WebElement passwordInput = wait(signUpPasswordLocator);
        passwordInput.sendKeys(password);
        Assert.assertEquals(getElementValue(passwordInput), password);

        //Birth Date
        this.selectBirthDay(birthDay, birthMonth, birthYear);

        //Address Information
        this.fillAddressInformation(Name, Company, Address1, Address2, Country, City, State, ZipCode, phoneNumber);

        //Click Create Account button
        WebElement signUpCreateAccountButton = getButtonByText(signUpCreateAccountButtonText);
        signUpCreateAccountButton.click();

        this.verifySuccessSignUpPageLoaded();
    }

    public HomePage clickContinueButton(boolean verify){
        WebElement continueButton = getLinkByText(signUpContinueButtonText);
        continueButton.click();
        return new HomePage(driver,wait,verify);
    }

    public void fillLogin(String email, String password){
        WebElement emailInput = wait(loginEmailInputLocator);
        WebElement passwordInput = wait(loginPasswordInputLocator);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
    }

    public HomePage clickLoginButton(boolean verify){
        WebElement loginButton = getButtonByText(loginButtonText);
        loginButton.click();
        return new HomePage(driver,wait,verify);
    }

    public void verifyLoginPageText(){
        WebElement loginText = getByTextContains(loginAccountText);
        Assert.assertTrue(loginText.isDisplayed());
    }

    public void verifyErrorIncorrectDataMessage (){
        WebElement errorMessage = getByTextContains(loginErrorIncorrectDataMessage);
        Assert.assertTrue(errorMessage.isDisplayed());
    }

    public void verifySignUpLoginButtonVisible(){
        WebElement signUpLoginButton = wait(navigationBarSignUpLogin);
        Assert.assertTrue(signUpLoginButton.isDisplayed());
        Assert.assertTrue(signUpLoginButton.isEnabled());
    }

    public HomePage clickHomeButton(boolean verify){
        WebElement homeButton = getLinkByText(homeButtonText);
        homeButton.click();
        return new HomePage(driver,wait,verify);
    }

    public void verifyEmailExistMessage(){
        WebElement errorMessage = getByTextContains(signUpEmailAlreadyExistErrorMessage);
        Assert.assertTrue(errorMessage.isEnabled());
    }
}
