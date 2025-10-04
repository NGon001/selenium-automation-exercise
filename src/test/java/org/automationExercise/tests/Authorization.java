package org.automationExercise.tests;

import org.automationExercise.pages.BaseTest;
import org.automationExercise.pages.HomePage;
import org.automationExercise.pages.SignUpLoginPage;
import org.testng.annotations.Test;

import static org.automationExercise.pages.Utils.generateRandomEmail;

public class Authorization extends BaseTest {


     /*
  1. Launch browser
  2. Navigate to url 'http://automationexercise.com'
  3. Verify that home page is visible successfully
  4. Click on 'Signup / Login' button
  5. Verify 'New User Signup!' is visible
  6. Enter name and email address
  7. Click 'Signup' button
  8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
  9. Fill details: Title, Name, Email, Password, Date of birth
  10. Select checkbox 'Sign up for our newsletter!'
  11. Select checkbox 'Receive special offers from our partners!'
  12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
  13. Click 'Create Account button'
  14. Verify that 'ACCOUNT CREATED!' is visible
  15. Click 'Continue' button
  16. Verify that 'Logged in as username' is visible
  17. Click 'Delete Account' button
  18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button */

    @Test
    public void registerUser(){
        String fullName = dotenv.get("REGISTER_NAME_FIRST") + " " + dotenv.get("REGISTER_NAME_LAST");
        String email = generateRandomEmail();

        HomePage homePage = new HomePage(driver,wait);
        homePage.verifyHomePageLoad();

        SignUpLoginPage signUpLoginPage = homePage.navigateToSignUpLoginPage();
        signUpLoginPage.verifySignUpPageLoaded();

        signUpLoginPage.fillShortSigUpForm(fullName, email);
        signUpLoginPage.clickSignUpButton();
        signUpLoginPage.verefieSignUpFullFormLoaded();
        signUpLoginPage.fillLoginSignUpForm(
                dotenv.get("REGISTER_TITLE"),
                fullName,
                email,
                dotenv.get("REGISTER_PASSWORD"),
                dotenv.get("REGISTER_BIRTH_DAY"),
                dotenv.get("REGISTER_BIRTH_MONTH"),
                dotenv.get("REGISTER_BIRTH_YEAR"),
                dotenv.get("REGISTER_COMPANY_NAME"),
                dotenv.get("REGISTER_ADDRESS"),
                dotenv.get("REGISTER_ADDRESS2"),
                dotenv.get("REGISTER_COUNTRY"),
                dotenv.get("REGISTER_STATE"),
                dotenv.get("REGISTER_CITY"),
                dotenv.get("REGISTER_ZIPCODE"),
                dotenv.get("REGISTER_MOBILE_NUMBER")
        );
        homePage = signUpLoginPage.clickContinueButton();
        homePage.verifyHomePageLoad();
        homePage.verifyLoggedInAs(fullName);
        homePage.clickDeleteAccountButton();
        homePage.verifyAccountDeletedMessage();
        homePage.clickContinueButton();
        homePage.verifyHomePageLoad();
    }

      /*
  1. Launch browser
  2. Navigate to url 'http://automationexercise.com'
  3. Verify that home page is visible successfully
  4. Click on 'Signup / Login' button
  5. Verify 'Login to your account' is visible
  6. Enter correct email address and password
  7. Click 'login' button
  8. Verify that 'Logged in as username' is visible
  */

    @Test
    public void loginUserWithCorrectData(){
        HomePage homePage = new HomePage(driver,wait);
        homePage.verifyHomePageLoad();
        SignUpLoginPage signUpLoginPage = homePage.navigateToSignUpLoginPage();
        signUpLoginPage.verifyLoginPageText();
        signUpLoginPage.fillLogin(dotenv.get("VALID_LOGIN_EMAIL"),dotenv.get("VALID_LOGIN_PASSWORD"));
        homePage = signUpLoginPage.clickLoginButton();
        homePage.verifyHomePageLoad();
        homePage.verifyLoggedInAs(dotenv.get("VALID_LOGIN_NAME_FIRST"));
    }
}
