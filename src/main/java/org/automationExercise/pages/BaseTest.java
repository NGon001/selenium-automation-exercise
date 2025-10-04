package org.automationExercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.remote.http.Contents;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.lang.reflect.Method;
import java.time.Duration;

import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    public static final Dotenv dotenv = Dotenv.load();
    protected WebDriver driver;
    protected WebDriverWait wait;
    private NetworkInterceptor interceptor;

    @BeforeMethod
    public void setUp(Method method) throws Exception {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Route blockAds = Route.matching(req -> {
            String url = req.getUri();
            return url.contains("googleads")
                    || url.contains("doubleclick")
                    || url.contains("googlesyndication");
        }).to(() -> req -> new HttpResponse()
                .setStatus(403)
                .setContent(Contents.utf8String("Blocked")));

        // Keep interceptor alive for whole test run
        this.interceptor = new NetworkInterceptor(driver, blockAds);

        this.driver.manage().window().maximize();
        ScreenRecord.startRecording(method.getName());
        this.driver.get("http://automationexercise.com");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if (this.interceptor != null) {
            this.interceptor.close();
        }
        if (this.driver != null) {
            this.driver.quit();
        }
        ScreenRecord.stopRecording();
    }
}
