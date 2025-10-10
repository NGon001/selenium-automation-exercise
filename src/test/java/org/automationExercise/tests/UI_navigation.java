package org.automationExercise.tests;

import org.automationExercise.pages.BaseTest;
import org.automationExercise.pages.HomePage;
import org.automationExercise.pages.TestCasesPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UI_navigation extends BaseTest {
    HomePage homePage;

    @BeforeMethod
    public void beforeTest(){
        homePage = new HomePage(driver,wait,true);
    }

    @Test
    public void navigateToTestCases(){
        homePage.navigateToTestCasesPage(true);
    }
}
