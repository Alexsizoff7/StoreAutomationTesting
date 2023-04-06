package teststore.ui.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

import static teststore.DriverFactory.getChromeDriver;

public class BaseClass {

    protected WebDriver driver = getChromeDriver();

    @BeforeSuite
    public void startUpBrowser() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void goToHomePage() {
        driver.get("http://teststore.automationtesting.co.uk/");
    }

    @AfterSuite(alwaysRun = true)
    public void closeBrowser() {
        driver.close();
    }

}
