package org.automationExercise.tests;

import org.automationExercise.pages.BaseTest;
import org.automationExercise.pages.HomePage;
import org.automationExercise.pages.TestCasesPage;
import org.testng.annotations.Test;

public class UI_navigation extends BaseTest {

    @Test
    public void navigateToHomePage(){
        HomePage homePage = new HomePage(driver, wait);
        homePage.verifyHomePageLoad();
    }

    @Test
    public void navigateToTestCases(){
        HomePage homePage = new HomePage(driver, wait);
        homePage.verifyHomePageLoad();
        TestCasesPage testCasesPage = homePage.navigateToTestCasesPage();
        testCasesPage.verifyTestCasePageLoaded();
    }
}
